package com.jobexchange.resource.dto;

import static com.jobexchange.model.Job.JobBuilder.aJob;

import com.jobexchange.model.Amount;
import com.jobexchange.model.Job;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Set;

public class JobDTO {

    private boolean driverLicenseRequired;
    private Set<String> requiredCertificates;
    private LocationDTO location;
    private String billRate;
    private int workersRequired;
    private LocalDateTime startDate;
    private String about;
    private String jobTitle;
    private String company;
    private String guid;
    private long jobId;


    public boolean isDriverLicenseRequired() {
        return driverLicenseRequired;
    }

    public Set<String> getRequiredCertificates() {
        return requiredCertificates;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public String getBillRate() {
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
    public String toString() {
        return "JobDTO{" +
            "driverLicenseRequired=" + driverLicenseRequired +
            ", requiredCertificates=" + requiredCertificates +
            ", location=" + location +
            ", billRate='" + billRate + '\'' +
            ", workersRequired=" + workersRequired +
            ", startDate=" + startDate +
            ", about='" + about + '\'' +
            ", jobTitle='" + jobTitle + '\'' +
            ", company='" + company + '\'' +
            ", guid='" + guid + '\'' +
            ", jobId=" + jobId +
            '}';
    }

    public static Job toJob(JobDTO job) {
        return aJob(job.jobId, job.jobTitle, job.company)
            .withStartDate(job.startDate)
            .withLocation(LocationDTO.toLocation(job.location))
            .withDriverLicenseRequired(job.driverLicenseRequired)
            .withRequiredCertificates(job.getRequiredCertificates())
            .withAbout(job.about)
            .withBillRate(
                new Amount(Currency.getInstance("AUD"), new BigDecimal(job.billRate.substring(1))))
            .withGuid(job.guid)
            .withWorkersRequired(job.workersRequired)
            .build();
    }

    public static final class JobDTOBuilder {

        private boolean driverLicenseRequired;
        private Set<String> requiredCertificates;
        private LocationDTO location;
        private String billRate;
        private int workersRequired;
        private LocalDateTime startDate;
        private String about;
        private String jobTitle;
        private String company;
        private String guid;
        private long jobId;

        private JobDTOBuilder() {
        }

        public static JobDTOBuilder aJobDTO() {
            return new JobDTOBuilder();
        }

        public JobDTOBuilder withDriverLicenseRequired(boolean driverLicenseRequired) {
            this.driverLicenseRequired = driverLicenseRequired;
            return this;
        }

        public JobDTOBuilder withRequiredCertificates(Set<String> requiredCertificates) {
            this.requiredCertificates = requiredCertificates;
            return this;
        }

        public JobDTOBuilder withLocation(LocationDTO location) {
            this.location = location;
            return this;
        }

        public JobDTOBuilder withBillRate(String billRate) {
            this.billRate = billRate;
            return this;
        }

        public JobDTOBuilder withWorkersRequired(int workersRequired) {
            this.workersRequired = workersRequired;
            return this;
        }

        public JobDTOBuilder withStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public JobDTOBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public JobDTOBuilder withJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public JobDTOBuilder withCompany(String company) {
            this.company = company;
            return this;
        }

        public JobDTOBuilder withGuid(String guid) {
            this.guid = guid;
            return this;
        }

        public JobDTOBuilder withJobId(long jobId) {
            this.jobId = jobId;
            return this;
        }

        public JobDTO build() {
            JobDTO jobDTO = new JobDTO();
            jobDTO.startDate = this.startDate;
            jobDTO.jobId = this.jobId;
            jobDTO.workersRequired = this.workersRequired;
            jobDTO.requiredCertificates = this.requiredCertificates;
            jobDTO.location = this.location;
            jobDTO.jobTitle = this.jobTitle;
            jobDTO.guid = this.guid;
            jobDTO.driverLicenseRequired = this.driverLicenseRequired;
            jobDTO.company = this.company;
            jobDTO.billRate = this.billRate;
            jobDTO.about = this.about;
            return jobDTO;
        }
    }
}
