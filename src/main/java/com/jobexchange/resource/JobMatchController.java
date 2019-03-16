package com.jobexchange.resource;

import com.jobexchange.resource.dto.JobMatchesDTO;

public interface JobMatchController {

    String JOB_MATCH_CONTEXT = "/matches";

    JobMatchesDTO findMatches(long workerId);

}
