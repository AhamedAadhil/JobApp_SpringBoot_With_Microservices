package com.aathil.jobsms.job;

import com.aathil.jobsms.job.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO> >  findAll(){
       return ResponseEntity.ok(jobService.findAll()) ;  //another way
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
       jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully",HttpStatus.OK); //another way
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job= jobService.getJobByID(id);
            if(job!=null){
                return new ResponseEntity<>(job, HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
      boolean deleted= jobService.deleteJobByID(id);
      if(deleted){
          return ResponseEntity.ok("JOB DELETED");
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id,@RequestBody Job job){

    boolean updated = jobService.updateJobById(id,job);
    if(updated){
        return ResponseEntity.ok("JOB UPDATED");
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
