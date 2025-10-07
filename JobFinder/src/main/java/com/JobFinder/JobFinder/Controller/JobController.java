package com.JobFinder.JobFinder.Controller;

import com.JobFinder.JobFinder.Entity.Job;
import com.JobFinder.JobFinder.Services.JobService;
import com.JobFinder.JobFinder.payload.JobDTO;
import com.JobFinder.JobFinder.payload.JobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponse>createJobs(@RequestBody JobDTO jobDTO){
        JobResponse jobResponse = jobService.saveJob(jobDTO);
        return new ResponseEntity<>(jobResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully with id: " + id);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<JobResponse>> getJobsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(jobService.getJobsByCategory(category));
    }

    @GetMapping("/salary")
    public ResponseEntity<List<JobResponse>> getJobsBySalaryRange(
            @RequestParam Integer min,
            @RequestParam Integer max) {
        return ResponseEntity.ok(jobService.getJobsBySalaryRange(min, max));
    }

    @GetMapping("/unnotified")
    public ResponseEntity<List<JobResponse>> getUnnotifiedJobs() {
        List<JobResponse> newJobs = jobService.getUnnotifiedJobs();
        return new ResponseEntity<>(newJobs, HttpStatus.OK);
    }
}
