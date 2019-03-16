package com.jobexchange.service;

import com.jobexchange.model.Job;
import java.util.List;

public interface JobMatchService {

    List<Job> match(long workerId);

}
