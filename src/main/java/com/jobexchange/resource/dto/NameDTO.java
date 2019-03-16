package com.jobexchange.resource.dto;

import com.jobexchange.model.Name;

public class NameDTO {

    private String first;
    private String last;

    public static Name toName(NameDTO name) {
        return new Name(name.first, name.last);
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public String toString() {
        return "NameDTO{" +
            "first='" + first + '\'' +
            ", last='" + last + '\'' +
            '}';
    }
}
