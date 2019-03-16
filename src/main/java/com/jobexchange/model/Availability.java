package com.jobexchange.model;

import java.time.DayOfWeek;

public class Availability {

    private DayOfWeek dayOfWeek;

    public Availability(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public String toString() {
        return "Availability{" +
            "dayOfWeek=" + dayOfWeek +
            "index=" + dayOfWeek.getValue() +
            '}';
    }
}
