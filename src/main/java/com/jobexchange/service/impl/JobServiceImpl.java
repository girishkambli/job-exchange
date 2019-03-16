package com.jobexchange.service.impl;

import com.jobexchange.engine.JobMatchingEngine;
import com.jobexchange.model.Job;
import com.jobexchange.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobServiceImpl implements JobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

    private final JobMatchingEngine jobMatchingEngine;

    @Autowired
    public JobServiceImpl(JobMatchingEngine jobMatchingEngine) {
        this.jobMatchingEngine = jobMatchingEngine;
    }

    @Override
    public long add(Job job) {
        LOGGER.info("Adding job: {}", job);
        return jobMatchingEngine.add(job) ? job.getJobId() : -1;
    }

    @Override
    public void clear() {
        jobMatchingEngine.clear();
    }
}
