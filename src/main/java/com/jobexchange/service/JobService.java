package com.jobexchange.service;

import com.jobexchange.model.Job;

public interface JobService {

    long add(Job job);

    void clear();
}
