package com.jobexchange.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class Job {

    private boolean driverLicenseRequired;
    private Set<String> requiredCertificates;
    private Location location;
    private Amount billRate;
    private int workersRequired;
    private LocalDateTime startDate;
    private String about;
    private String jobTitle;
    private String company;
    private String guid;
    private long jobId;


    public static final class JobBuilder {

        private boolean driverLicenseRequired;
        private Set<String> requiredCertificates;
        private Location location;
        private Amount billRate;
        private int workersRequired;
        private LocalDateTime startDate;
        private String about;
        private String jobTitle;
        private String company;
        private String guid;
        private long jobId;

        private JobBuilder(long jobId, String jobTitle, String company) {
            this.jobId = jobId;
            this.jobTitle = jobTitle;
            this.company = company;
        }

        public static JobBuilder aJob(long jobId, String jobTitle, String company) {
            return new JobBuilder(jobId, jobTitle, company);
        }

        public JobBuilder withDriverLicenseRequired(boolean driverLicenseRequired) {
            this.driverLicenseRequired = driverLicenseRequired;
            return this;
        }

        public JobBuilder withRequiredCertificates(Set<String> requiredCertificates) {
            this.requiredCertificates = requiredCertificates;
            return this;
        }

        public JobBuilder withLocation(Location location) {
            this.location = location;
            return this;
        }

        public JobBuilder withBillRate(Amount billRate) {
            this.billRate = billRate;
            return this;
        }

        public JobBuilder withWorkersRequired(int workersRequired) {
            this.workersRequired = workersRequired;
            return this;
        }

        public JobBuilder withStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public JobBuilder withAbout(String about) {
            this.about = about;
            return this;
        }


        public JobBuilder withGuid(String guid) {
            this.guid = guid;
            return this;
        }


        public Job build() {
            Job job = new Job();
            job.driverLicenseRequired = this.driverLicenseRequired;
            job.location = this.location;
            job.company = this.company;
            job.requiredCertificates = this.requiredCertificates;
            job.about = this.about;
            job.guid = this.guid;
            job.billRate = this.billRate;
            job.jobId = this.jobId;
            job.workersRequired = this.workersRequired;
            job.startDate = this.startDate;
            job.jobTitle = this.jobTitle;
            return job;
        }
    }

    public boolean isDriverLicenseRequired() {
        return driverLicenseRequired;
    }

    public Set<String> getRequiredCertificates() {
        return requiredCertificates;
    }

    public Location getLocation() {
        return location;
    }

    public Amount getBillRate() {
        return billRate;
    }

    public int getWorkersRequired() {
        return workersRequired;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public String getAbout() {
        return about;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getGuid() {
        return guid;
    }

    public long getJobId() {
        return jobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Job job = (Job) o;
        return jobId == job.jobId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId);
    }

    @Override
    public String toString() {
        return "Job{" +
            "driverLicenseRequired=" + driverLicenseRequired +
            ", requiredCertificates=" + requiredCertificates +
            ", location=" + location +
            ", billRate=" + billRate +
            ", workersRequired=" + workersRequired +
            ", startDate=" + startDate +
            ", about='" + about + '\'' +
            ", jobTitle='" + jobTitle + '\'' +
            ", company='" + company + '\'' +
            ", guid='" + guid + '\'' +
            ", jobId=" + jobId +
            '}';
    }
}
