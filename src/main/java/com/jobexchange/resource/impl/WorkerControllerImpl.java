package com.jobexchange.resource.impl;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.jobexchange.resource.WorkerController;
import com.jobexchange.resource.dto.IdsDTO;
import com.jobexchange.resource.dto.WorkerDTO;
import com.jobexchange.service.WorkerService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkerControllerImpl implements WorkerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerControllerImpl.class);

    private final WorkerService workerService;

    @Autowired
    public WorkerControllerImpl(WorkerService workerService) {
        this.workerService = workerService;
    }

    @Override
    @RequestMapping(method = {POST},
        value = {WORKERS_CONTEXT},
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public IdsDTO<Long> upload(@RequestBody List<WorkerDTO> workers) {

        return IdsDTO.of(workers.stream().map(WorkerDTO::toWorker).map(workerService::add)
            .collect(Collectors.toList()));
    }

    @Override
    @RequestMapping(
        method = DELETE,
        value = WORKERS_CONTEXT
    )
    public void clear() {
        workerService.clear();
    }
}
