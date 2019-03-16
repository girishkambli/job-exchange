package com.jobexchange.resource;

import com.jobexchange.resource.dto.IdsDTO;
import com.jobexchange.resource.dto.WorkerDTO;
import java.util.List;

public interface WorkerController {

    String WORKERS_CONTEXT = "/workers";

    IdsDTO<Long> upload(List<WorkerDTO> workers);

    void clear();

}
