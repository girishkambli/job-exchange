package com.jobexchange.resource.dto;

import static com.jobexchange.model.Worker.WorkerBuilder.aWorker;
import static com.jobexchange.resource.dto.NameDTO.toName;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobexchange.model.Worker;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkerDTO {

    private int rating;

    @JsonProperty(value="isActive")
    private boolean isActive;

    private Set<String> certificates;
    private Set<String> skills;
    private JobSearchAddressDTO jobSearchAddress;
    private String transportation;
    private boolean hasDriversLicense;
    private List<AvailabilityDTO> availability;
    private String phone;
    private String email;
    private NameDTO name;
    private int age;
    private String guid;
    private long userId;

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

    public JobSearchAddressDTO getJobSearchAddress() {
        return jobSearchAddress;
    }

    public String getTransportation() {
        return transportation;
    }

    public boolean isHasDriversLicense() {
        return hasDriversLicense;
    }

    public List<AvailabilityDTO> getAvailability() {
        return availability;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public NameDTO getName() {
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
    public String toString() {
        return "WorkerDTO{" +
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

    public static Worker toWorker(WorkerDTO worker) {
        return aWorker(worker.userId, toName(worker.name), worker.isActive)
            .withAge(worker.age)
            .withAvailability(
                worker.availability.stream().map(AvailabilityDTO::toAvailability).collect(
                    Collectors.toSet()))
            .withCertificates(worker.certificates)
            .withEmail(worker.email)
            .withGuid(worker.guid)
            .withHasDriversLicense(worker.hasDriversLicense)
            .withJobSearchAddress(JobSearchAddressDTO.toJobSearchAdress(worker.jobSearchAddress))
            .withPhone(worker.phone)
            .withRating(worker.rating)
            .withSkills(worker.skills)
            .withTransportation(worker.transportation)
            .build();
    }
}

