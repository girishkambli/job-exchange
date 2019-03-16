package com.jobexchange.engine.predicate;

import com.jobexchange.model.Availability;
import com.jobexchange.model.Job;
import com.jobexchange.model.JobSearchAddress;
import com.jobexchange.model.JobSearchAddress.DistanceUnit;
import com.jobexchange.model.Location;
import com.jobexchange.model.Worker;
import com.jobexchange.util.DistanceCalculator;
import java.time.DayOfWeek;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Predicates {

    private static final Logger LOGGER = LoggerFactory.getLogger(Predicates.class);

    private final DistanceCalculator distanceCalculator;

    @Autowired
    public Predicates(DistanceCalculator calculator) {
        this.distanceCalculator = calculator;
    }

    public Predicate<Worker> isWorkerActive() {
        return worker -> {
            boolean active = worker.isActive();
            LOGGER.debug("WorkerId={}, isWorkerActive = {}", worker.getUserId(), active);

            return active;
        };
    }

    public BiPredicate<Job, Worker> matchDrivingLicense() {
        return (job, worker) -> {
            boolean dlRequired =
                job.isDriverLicenseRequired() ? worker.isHasDriversLicense() : true;
            LOGGER.debug("WorkerId={}, matchDrivingLicense = {}", worker.getUserId(),
                dlRequired);

            return dlRequired;
        };
    }

    public BiPredicate<Job, Worker> matchCertificates() {
        return (job, worker) -> {
            boolean match = worker.getCertificates().containsAll(job.getRequiredCertificates());
            LOGGER.debug("WorkerId={}, matchCertificates = {}", worker.getUserId(), match);

            return match;
        };
    }

    public BiPredicate<Job, Worker> matchSkills() {
        return (job, worker) -> {
            boolean match = worker.getSkills().contains(job.getJobTitle());
            LOGGER.debug("WorkerId={}, matchSkills = {}", worker.getUserId(), match);

            return match;
        };
    }

    public BiPredicate<Job, Worker> matchStartDay() {
        return (job, worker) -> {
            boolean match = worker.getAvailability().stream().map(Availability::getDayOfWeek)
                .anyMatch(d -> d == DayOfWeek.from(job.getStartDate()));
            LOGGER.debug("WorkerId={}, matchStartDay = {}", worker.getUserId(), match);

            return match;
        };
    }

    public BiPredicate<Job, Worker> matchDistance() {
        return (job, worker) -> {
            JobSearchAddress jobSearchAddress = worker.getJobSearchAddress();
            Location workerLocation = jobSearchAddress.getLocation();
            Location jobLocation = job.getLocation();

            double jobDistance = distanceCalculator.calculate(jobLocation, workerLocation);
            LOGGER.debug("WorkerId={}, JobId={}, Worker to Job location distance = {}",
                worker.getUserId(), job.getJobId(), jobDistance);

            boolean match = Double.compare(jobDistance, jobSearchAddress.getUnit()
                .toUnit(DistanceUnit.Meter, jobSearchAddress.getMaxJobDistance())) <= 0;
            LOGGER.debug("WorkerId={}, matchDistance = {}", worker.getUserId(), match);

            return match;
        };
    }

}
