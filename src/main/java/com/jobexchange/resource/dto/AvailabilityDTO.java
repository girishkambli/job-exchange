package com.jobexchange.resource.dto;

import com.jobexchange.model.Availability;
import java.time.DayOfWeek;
import java.util.Optional;

public class AvailabilityDTO {

    private String title;
    private int dayIndex;

    public static Availability toAvailability(AvailabilityDTO availability) {
        return Optional.ofNullable(availability)
            .map(AvailabilityDTO::getTitle)
            .map(String::toUpperCase)
            .map(DayOfWeek::valueOf)
            .map(Availability::new)
            .orElse(null);
    }

    public String getTitle() {
        return title;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    @Override
    public String toString() {
        return "AvailabilityDTO{" +
            "title='" + title + '\'' +
            ", dayIndex=" + dayIndex +
            '}';
    }
}
