package com.jobexchange.service.impl;

import com.jobexchange.model.Worker;
import com.jobexchange.service.WorkerService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WorkerServiceImpl implements WorkerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerServiceImpl.class);

    private Map<Long, Worker> workers;

    public WorkerServiceImpl() {
        this.workers = new ConcurrentHashMap<>();
    }

    @Override
    public long add(Worker worker) {
        LOGGER.info("Adding worker: {}", worker);
        workers.put(worker.getUserId(), worker);
        return worker.getUserId();
    }

    @Override
    public Worker get(long workerId) {
        return workers.get(workerId);
    }

    @Override
    public void clear() {
        workers.clear();
    }
}
