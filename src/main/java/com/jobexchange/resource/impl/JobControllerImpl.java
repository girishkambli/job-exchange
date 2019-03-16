package com.jobexchange.resource.impl;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.jobexchange.resource.JobController;
import com.jobexchange.resource.dto.IdsDTO;
import com.jobexchange.resource.dto.JobDTO;
import com.jobexchange.service.JobService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobControllerImpl implements JobController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobControllerImpl.class);

    private final JobService jobService;

    @Autowired
    public JobControllerImpl(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    @RequestMapping(method = {POST},
        value = {JOBS_CONTEXT},
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
    )
    public IdsDTO<Long> upload(@RequestBody List<JobDTO> jobs) {

        return IdsDTO
            .of(jobs.stream().map(JobDTO::toJob).map(jobService::add).collect(Collectors.toList()));

    }

    @Override
    @RequestMapping(
        method = DELETE,
        value = JOBS_CONTEXT
    )
    public void clear() {
        jobService.clear();
    }
}
