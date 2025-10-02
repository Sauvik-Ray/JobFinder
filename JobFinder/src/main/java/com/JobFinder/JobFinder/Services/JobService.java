package com.JobFinder.JobFinder.Services;

import com.JobFinder.JobFinder.payload.JobDTO;
import com.JobFinder.JobFinder.payload.JobResponse;

import java.util.List;

public interface JobService {
    JobResponse saveJob(JobDTO jobDTO);

    List<JobResponse> getAllJobs();

    JobResponse getJobById(Long id);

    void deleteJob(Long id);

    List<JobResponse> getJobsByCategory(String category);

    List<JobResponse> getJobsBySalaryRange(Integer minSalary, Integer maxSalary);
}
