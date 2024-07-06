package com.aathil.jobsms.job.impl;


import com.aathil.jobsms.job.Job;
import com.aathil.jobsms.job.JobRepository;
import com.aathil.jobsms.job.JobService;
import com.aathil.jobsms.job.dto.JobWithCompanyDTO;
import com.aathil.jobsms.job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS= new ArrayList<>();
//        Rest  Template call
        RestTemplate restTemplate = new RestTemplate();
        for (Job job:jobs
             ) {
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
        Company company= restTemplate.getForObject("http://localhost:8081/company/"+job.getCompanyId(), Company.class);
        jobWithCompanyDTO.setCompany(company);

        jobWithCompanyDTOS.add(jobWithCompanyDTO);
        }

        return jobWithCompanyDTOS;
    }


    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobByID(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobByID(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateJobById(Long id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            Job jb = jobOptional.get();
            jb.setTitle(job.getTitle());
            jb.setDescription(job.getDescription());
            jb.setMinSalary(job.getMinSalary());
            jb.setMaxSalary(job.getMaxSalary());
            jb.setLocation(job.getLocation());
            jobRepository.save(jb);
            return true;

        }
        return false;
    }

}
