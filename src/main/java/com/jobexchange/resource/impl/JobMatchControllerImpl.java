package com.jobexchange.resource.impl;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.jobexchange.resource.JobMatchController;
import com.jobexchange.resource.dto.JobMatchesDTO;
import com.jobexchange.service.JobMatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobMatchControllerImpl implements JobMatchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatchControllerImpl.class);

    private final JobMatchService jobMatchService;

    @Autowired
    public JobMatchControllerImpl(JobMatchService jobMatchService) {
        this.jobMatchService = jobMatchService;
    }

    @Override
    @RequestMapping(method = {GET},
        value = {JOB_MATCH_CONTEXT + "/{workerId}"},
        produces = APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public JobMatchesDTO findMatches(@PathVariable("workerId") long workerId) {

        LOGGER.info("Received match request for worker-id {}", workerId);

        return JobMatchesDTO.from(jobMatchService.match(workerId));
    }
}
