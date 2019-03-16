package com.jobexchange.model;

import java.util.Objects;
import java.util.Set;

public class Worker {

    private int rating;
    private boolean isActive;
    private Set<String> certificates;
    private Set<String> skills;
    private JobSearchAddress jobSearchAddress;
    private String transportation;
    private boolean hasDriversLicense;
    private Set<Availability> availability;
    private String phone;
    private String email;
    private Name name;
    private int age;
    private String guid;
    private long userId;


    public static final class WorkerBuilder {

        private int rating;
        private boolean isActive;
        private Set<String> certificates;
        private Set<String> skills;
        private JobSearchAddress jobSearchAddress;
        private String transportation;
        private boolean hasDriversLicense;
        private Set<Availability> availability;
        private String phone;
        private String email;
        private Name name;
        private int age;
        private String guid;
        private long userId;

        private WorkerBuilder(long userId, Name name, boolean isActive) {
            this.userId = userId;
            this.name = name;
            this.isActive = isActive;
        }

        public static WorkerBuilder aWorker(long userId, Name name, boolean isActive) {
            return new WorkerBuilder(userId, name, isActive);
        }

        public WorkerBuilder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        public WorkerBuilder withCertificates(Set<String> certificates) {
            this.certificates = certificates;
            return this;
        }

        public WorkerBuilder withSkills(Set<String> skills) {
            this.skills = skills;
            return this;
        }

        public WorkerBuilder withJobSearchAddress(JobSearchAddress jobSearchAddress) {
            this.jobSearchAddress = jobSearchAddress;
            return this;
        }

        public WorkerBuilder withTransportation(String transportation) {
            this.transportation = transportation;
            return this;
        }

        public WorkerBuilder withHasDriversLicense(boolean hasDriversLicense) {
            this.hasDriversLicense = hasDriversLicense;
            return this;
        }

        public WorkerBuilder withAvailability(Set<Availability> availability) {
            this.availability = availability;
            return this;
        }

        public WorkerBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public WorkerBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public WorkerBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public WorkerBuilder withGuid(String guid) {
            this.guid = guid;
            return this;
        }

        public Worker build() {
            Worker worker = new Worker();
            worker.transportation = this.transportation;
            worker.name = this.name;
            worker.rating = this.rating;
            worker.isActive = this.isActive;
            worker.availability = this.availability;
            worker.userId = this.userId;
            worker.skills = this.skills;
            worker.hasDriversLicense = this.hasDriversLicense;
            worker.age = this.age;
            worker.guid = this.guid;
            worker.email = this.email;
            worker.jobSearchAddress = this.jobSearchAddress;
            worker.certificates = this.certificates;
            worker.phone = this.phone;
            return worker;
        }
    }

    public int getRating() {
        return rating;
    }

    public boolean isActive() {
        return isActive;
    }

    public Set<String> getCertificates() {
        return certificates;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public JobSearchAddress getJobSearchAddress() {
        return jobSearchAddress;
    }

    public String getTransportation() {
        return transportation;
    }

    public boolean isHasDriversLicense() {
        return hasDriversLicense;
    }

    public Set<Availability> getAvailability() {
        return availability;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGuid() {
        return guid;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker worker = (Worker) o;
        return userId == worker.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "Worker{" +
            "rating=" + rating +
            ", isActive=" + isActive +
            ", certificates=" + certificates +
            ", skills=" + skills +
            ", jobSearchAddress=" + jobSearchAddress +
            ", transportation='" + transportation + '\'' +
            ", hasDriversLicense=" + hasDriversLicense +
            ", availability=" + availability +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", name=" + name +
            ", age=" + age +
            ", guid='" + guid + '\'' +
            ", userId=" + userId +
            '}';
    }
}
