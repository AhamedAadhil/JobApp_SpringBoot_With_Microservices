package com.aathil.jobsms.job;

import com.aathil.jobsms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);
    Job getJobByID(Long id);

    boolean deleteJobByID(Long id);

    boolean updateJobById(Long id, Job job);
}
