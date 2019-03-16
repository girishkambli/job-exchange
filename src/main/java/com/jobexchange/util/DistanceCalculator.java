package com.jobexchange.util;

import com.jobexchange.model.Location;
import org.springframework.stereotype.Component;

@Component
public class DistanceCalculator {

    /**
     * Calculate distance between two points in latitude and longitude. Uses Haversine method as its
     * base.
     *
     * lat1, lon1 Start point lat2, lon2 End point in meters
     *
     * @returns Distance in Meters
     */
    public double calculate(Location jobLocation, Location workerLocation) {

        double lat1 = jobLocation.getLatitude();
        double lat2 = workerLocation.getLatitude();
        double lon1 = jobLocation.getLongitude();
        double lon2 = workerLocation.getLongitude();

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
