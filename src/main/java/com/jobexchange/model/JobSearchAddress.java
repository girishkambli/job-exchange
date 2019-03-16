package com.jobexchange.model;

import java.util.Arrays;

public class JobSearchAddress {

    public enum DistanceUnit {
        Kilometer("km") {
            public long toUnit(DistanceUnit unit, long d) {
                switch (unit) {
                    case Meter:
                        return d * 1000;
                }
                return d;
            }
        }, Meter("mtr") {
            public long toUnit(DistanceUnit unit, long d) {
                switch (unit) {
                    case Kilometer:
                        return d / 1000;
                }
                return d;
            }
        };

        private final String name;

        DistanceUnit(String name) {
            this.name = name;
        }

        public static DistanceUnit fromName(String name) {
            return Arrays.stream(DistanceUnit.values()).filter(v -> v.name.equals(name)).findAny()
                .orElse(null);
        }

        public abstract long toUnit(DistanceUnit unit, long d);
    }

    private DistanceUnit unit;
    private long maxJobDistance;
    private Location location;

    public JobSearchAddress(DistanceUnit unit, long maxJobDistance,
        Location location) {
        this.unit = unit;
        this.maxJobDistance = maxJobDistance;
        this.location = location;
    }

    public DistanceUnit getUnit() {
        return unit;
    }

    public long getMaxJobDistance() {
        return maxJobDistance;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "JobSearchAddress{" +
            "unit=" + unit +
            ", maxJobDistance=" + maxJobDistance +
            ", location=" + location +
            '}';
    }
}
