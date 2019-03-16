package com.jobexchange.engine;

import static com.jobexchange.model.Job.JobBuilder.aJob;
import static com.jobexchange.model.Worker.WorkerBuilder.aWorker;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.Sets;
import com.jobexchange.engine.predicate.Predicates;
import com.jobexchange.model.Availability;
import com.jobexchange.model.Job;
import com.jobexchange.model.JobSearchAddress;
import com.jobexchange.model.JobSearchAddress.DistanceUnit;
import com.jobexchange.model.Location;
import com.jobexchange.model.Name;
import com.jobexchange.model.Worker;
import com.jobexchange.util.DistanceCalculator;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.Test;

public class PredicatesTest {

    public static final int JOB_ID = 123;
    public static final String JOB_TITLE = "TestJob";
    public static final String COMPANY = "XYZ Ltd";
    public static final int USER_ID = 999;
    public static final Name NAME = new Name("fName", "lName");

    DistanceCalculator calculator = mock(DistanceCalculator.class);
    private Predicates predicates = new Predicates(calculator);

    @Test
    public void testWhenWorkerHasRequiredCertificatesThenMatchPasses() {
        Job job = aJob(JOB_ID, JOB_TITLE, COMPANY)
            .withRequiredCertificates(Sets.newHashSet("A", "B", "C"))
            .build();
        Worker worker = aWorker(USER_ID, NAME, true)
            .withCertificates(Sets.newHashSet("D", "A", "C", "V", "B", "Z"))
            .build();
        boolean match = predicates.matchCertificates().test(job, worker);

        assertThat(match, is(true));
    }

    @Test
    public void testWhenWorkerDoesNotHaveRequiredCertificatesThenMatchFails() {
        Job job = aJob(JOB_ID, JOB_TITLE, COMPANY)
            .withRequiredCertificates(Sets.newHashSet("A", "B", "C"))
            .build();
        Worker worker = aWorker(USER_ID, NAME, true)
            .withCertificates(Sets.newHashSet("D", "A", "C", "V", "Z"))
            .build();
        boolean match = predicates.matchCertificates().test(job, worker);

        assertThat(match, is(false));
    }

    @Test
    public void testWhenWorkerIsActiveThenMatchPasses() {
        Worker worker = aWorker(USER_ID, NAME, true)
            .build();
        boolean match = predicates.isWorkerActive().test(worker);
        assertThat(match, is(true));
    }

    @Test
    public void testWhenWorkerIsInActiveThenMatchFails() {
        Worker worker = aWorker(USER_ID, NAME, false)
            .build();
        boolean match = predicates.isWorkerActive().test(worker);
        assertThat(match, is(false));
    }

    @Test
    public void testWhenSkillsHaveJobTitleThenMatchPasses() {
        Worker worker = aWorker(USER_ID, NAME, true)
            .withSkills(Sets.newHashSet("A", "B", "C", "D"))
            .build();
        Job job = aJob(JOB_ID, "C", COMPANY)
            .build();
        boolean match = predicates.matchSkills().test(job, worker);
        assertThat(match, is(true));
    }

    @Test
    public void testWhenSkillsDoNotHaveJobTitleThenMatchFails() {
        Worker worker = aWorker(USER_ID, NAME, true)
            .withSkills(Sets.newHashSet("A", "B", "C", "D"))
            .build();
        Job job = aJob(JOB_ID, "H", COMPANY)
            .build();
        boolean match = predicates.matchSkills().test(job, worker);
        assertThat(match, is(false));
    }

    @Test
    public void testWhenDrivingLicenseRequiredAndWorkerHasDLThenMatchPasses() {
        Worker worker = aWorker(USER_ID, NAME, true)
            .withHasDriversLicense(true)
            .build();
        Job job = aJob(JOB_ID, JOB_TITLE, COMPANY)
            .withDriverLicenseRequired(true)
            .build();
        boolean match = predicates.matchDrivingLicense().test(job, worker);
        assertThat(match, is(true));
    }

    @Test
    public void testWhenDrivingLicenseRequiredAndWorkerDoesntHaveDLThenMatchFails() {
        Worker worker = aWorker(USER_ID, NAME, true)
            .withHasDriversLicense(false)
            .build();
        Job job = aJob(JOB_ID, JOB_TITLE, COMPANY)
            .withDriverLicenseRequired(true)
            .build();
        boolean match = predicates.matchDrivingLicense().test(job, worker);
        assertThat(match, is(false));
    }

    @Test
    public void testWhenDrivingLicenseNotRequiredAndWorkerHasDLThenMatchPasses() {
        Worker worker = aWorker(USER_ID, NAME, true)
            .withHasDriversLicense(true)
            .build();
        Job job = aJob(JOB_ID, JOB_TITLE, COMPANY)
            .withDriverLicenseRequired(false)
            .build();
        boolean match = predicates.matchDrivingLicense().test(job, worker);
        assertThat(match, is(true));
    }

    @Test
    public void testWhenDrivingLicenseNotRequiredAndWorkerDoesntDLThenMatchPasses() {
        Worker worker = aWorker(USER_ID, NAME, true)
            .withHasDriversLicense(true)
            .build();
        Job job = aJob(JOB_ID, JOB_TITLE, COMPANY)
            .withDriverLicenseRequired(false)
            .build();
        boolean match = predicates.matchDrivingLicense().test(job, worker);
        assertThat(match, is(true));
    }

    @Test
    public void testWhenJobIsWithinMaxDistanceThenMatchPasses() {
        Worker worker = aWorker(USER_ID, NAME, true)
            .withJobSearchAddress(new JobSearchAddress(DistanceUnit.Kilometer, 30,
                new Location(13.864602, 49.93359)))
            .build();
        Job job = aJob(JOB_ID, JOB_TITLE, COMPANY)
            .withLocation(new Location(13.971284, 49.782281))
            .build();

        when(calculator.calculate(any(Location.class), any(Location.class))).thenReturn(28000d);

        boolean match = predicates.matchDistance().test(job, worker);
        assertThat(match, is(true));
    }

    @Test
    public void testWhenJobIsOutsideMaxDistanceThenMatchFails() {
        Worker worker = aWorker(USER_ID, NAME, true)
            .withJobSearchAddress(new JobSearchAddress(DistanceUnit.Kilometer, 50,
                new Location(13.864602, 50.93359)))
            .build();
        Job job = aJob(JOB_ID, JOB_TITLE, COMPANY)
            .withLocation(new Location(13.971284, 49.782281))
            .build();

        when(calculator.calculate(any(Location.class), any(Location.class))).thenReturn(52000d);

        boolean match = predicates.matchDistance().test(job, worker);
        assertThat(match, is(false));
    }

    @Test
    public void testWhenJobStartDayIsInWorkersAvailibilityThenMatchPasses() {
        Worker worker = aWorker(USER_ID, NAME, true)
            .withAvailability(Sets.newHashSet(new Availability(DayOfWeek.THURSDAY),
                new Availability(DayOfWeek.MONDAY), new Availability(DayOfWeek.TUESDAY)))
            .build();
        Job job = aJob(JOB_ID, JOB_TITLE, COMPANY)
            .withStartDate(LocalDateTime.of(LocalDate.of(2019, 03, 12), LocalTime.NOON))
            .build();
        boolean match = predicates.matchStartDay().test(job, worker);
        assertThat(match, is(true));
    }

    @Test
    public void testWhenJobStartDayIsNotInWorkersAvailibilityThenMatchFails() {
        Worker worker = aWorker(USER_ID, NAME, true)
            .withAvailability(Sets.newHashSet(new Availability(DayOfWeek.THURSDAY),
                new Availability(DayOfWeek.MONDAY), new Availability(DayOfWeek.TUESDAY)))
            .build();
        Job job = aJob(JOB_ID, JOB_TITLE, COMPANY)
            .withStartDate(LocalDateTime.of(LocalDate.of(2019, 03, 15), LocalTime.NOON))
            .build();
        boolean match = predicates.matchStartDay().test(job, worker);
        assertThat(match, is(false));
    }

}
