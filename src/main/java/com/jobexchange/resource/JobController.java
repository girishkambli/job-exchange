package com.jobexchange.resource;

import com.jobexchange.resource.dto.IdsDTO;
import com.jobexchange.resource.dto.JobDTO;
import java.util.List;

public interface JobController {

    String JOBS_CONTEXT = "/jobs";

    IdsDTO<Long> upload(List<JobDTO> jobs);

    void clear();

}
