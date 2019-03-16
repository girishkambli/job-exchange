package com.jobexchange.engine;

import com.jobexchange.model.Job;
import com.jobexchange.model.Worker;
import java.util.List;

public interface JobMatchingEngine {

    boolean add(Job job);

    List<Job> match(Worker worker);

    void clear();
}
