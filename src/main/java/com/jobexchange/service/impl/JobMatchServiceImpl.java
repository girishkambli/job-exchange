package com.jobexchange.service.impl;

import com.jobexchange.engine.JobMatchingEngine;
import com.jobexchange.model.Job;
import com.jobexchange.service.JobMatchService;
import com.jobexchange.service.WorkerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobMatchServiceImpl implements JobMatchService {

    private final WorkerService workerService;
    private final JobMatchingEngine jobMatchingEngine;

    @Autowired
    public JobMatchServiceImpl(WorkerService workerService, JobMatchingEngine jobMatchingEngine) {
        this.workerService = workerService;
        this.jobMatchingEngine = jobMatchingEngine;
    }

    @Override
    public List<Job> match(long workerId) {
        return jobMatchingEngine.match(workerService.get(workerId));
    }
}
