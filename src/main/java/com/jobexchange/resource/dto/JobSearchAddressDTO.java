package com.jobexchange.resource.dto;

import static com.jobexchange.model.JobSearchAddress.DistanceUnit.fromName;

import com.jobexchange.model.JobSearchAddress;
import com.jobexchange.model.Location;
import java.util.Optional;

public class JobSearchAddressDTO {

    private String unit;
    private long maxJobDistance;
    private double longitude;
    private double latitude;

    public static JobSearchAddress toJobSearchAdress(JobSearchAddressDTO address) {
        return Optional.ofNullable(address)
            .map(j -> new JobSearchAddress(fromName(j.unit), j.maxJobDistance,
                new Location(address.latitude, address.longitude)))
            .orElse(null);
    }

    public String getUnit() {
        return unit;
    }

    public long getMaxJobDistance() {
        return maxJobDistance;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "JobSearchAddressDTO{" +
            "unit='" + unit + '\'' +
            ", maxJobDistance=" + maxJobDistance +
            ", longitude=" + longitude +
            ", latitude=" + latitude +
            '}';
    }
}
