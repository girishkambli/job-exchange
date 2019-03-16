package com.jobexchange.engine.impl;

import com.jobexchange.engine.JobMatchingEngine;
import com.jobexchange.engine.predicate.Predicates;
import com.jobexchange.model.Job;
import com.jobexchange.model.Worker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobMatchingEngineImpl implements JobMatchingEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatchingEngineImpl.class);

    private final List<Job> jobs;

    private final Predicates predicates;

    @Autowired
    public JobMatchingEngineImpl(Predicates predicates) {
        this.jobs = new ArrayList<>();
        this.predicates = predicates;
    }

    @Override
    public boolean add(Job job) {
        return jobs.add(job);
    }

    @Override
    public List<Job> match(Worker worker) {
        if (predicates.isWorkerActive().negate().test(worker)) {
            LOGGER.info("Worker with id {} is not active", worker.getUserId());
            return Collections.EMPTY_LIST;
        }

        return jobs.stream()
            .filter(job -> predicates.matchDrivingLicense().test(job, worker))
            .filter(job -> predicates.matchCertificates().test(job, worker))
            .filter(job -> predicates.matchSkills().test(job, worker))
            .filter(job -> predicates.matchDistance().test(job, worker))
            .filter(job -> predicates.matchStartDay().test(job, worker))
            .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        jobs.clear();
    }
}
