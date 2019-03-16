package com.jobexchange.service;

import com.jobexchange.model.Worker;

public interface WorkerService {

    long add(Worker worker);

    Worker get(long workerId);

    void clear();
}
