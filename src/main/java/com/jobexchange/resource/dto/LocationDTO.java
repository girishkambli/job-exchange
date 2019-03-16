package com.jobexchange.resource.dto;

import com.jobexchange.model.Location;
import java.util.Optional;

public class LocationDTO {

    private double longitude;
    private double latitude;

    public static Location toLocation(LocationDTO location) {
        return Optional.ofNullable(location)
            .map(l -> new Location(l.latitude, l.longitude))
            .orElse(null);
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
            "longitude=" + longitude +
            ", latitude=" + latitude +
            '}';
    }
}
