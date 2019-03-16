package com.jobexchange.resource.dto;

import com.jobexchange.model.Job;
import java.util.List;
import java.util.stream.Collectors;

public class JobMatchesDTO {

    private int count;
    private List<JobDTO> jobs;

    public JobMatchesDTO() {
    }

    public JobMatchesDTO(List<JobDTO> jobs) {
        this.jobs = jobs;
        this.count = jobs.size();
    }

    public static JobMatchesDTO from(List<Job> jobs) {
        if (jobs == null || jobs.size() == 0) {
            return new JobMatchesDTO();
        }
        return new JobMatchesDTO(
            jobs.stream().map(JobMatchesDTO::from).collect(Collectors.toList()));
    }

    private static JobDTO from(Job job) {
        return JobDTO.JobDTOBuilder.aJobDTO()
            .withJobId(job.getJobId())
            .withCompany(job.getCompany())
            .withJobTitle(job.getJobTitle())
            .withWorkersRequired(job.getWorkersRequired())
            .build();
    }

    public int getCount() {
        return count;
    }

    public List<JobDTO> getMatches() {
        return jobs;
    }

    @Override
    public String toString() {
        return "JobMatchesDTO{" +
            "count=" + count +
            ", jobs=" + jobs +
            '}';
    }
}
