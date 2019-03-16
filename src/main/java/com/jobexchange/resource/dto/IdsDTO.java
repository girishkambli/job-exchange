package com.jobexchange.resource.dto;

import java.util.List;

public class IdsDTO<T> {

    private List<T> ids;

    private IdsDTO(List<T> ids) {
        this.ids = ids;
    }

    public static <T> IdsDTO<T> of(List<T> ids) {
        return new IdsDTO<>(ids);
    }

    public List<T> getIds() {
        return ids;
    }
}
