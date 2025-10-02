package com.JobFinder.JobFinder.Services;

import com.JobFinder.JobFinder.Entity.Job;
import com.JobFinder.JobFinder.Repository.JobRepository;
import com.JobFinder.JobFinder.payload.JobDTO;
import com.JobFinder.JobFinder.payload.JobResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public JobResponse saveJob(JobDTO jobDTO) {
        // Prevent duplicate jobs by GUID
        Optional<Job> existingJob = jobRepository.findByGuid(jobDTO.getGuid());
        if (existingJob.isPresent()) {
            return modelMapper.map(existingJob.get(), JobResponse.class);
        }

        Job job = modelMapper.map(jobDTO, Job.class);
        Job savedJob = jobRepository.save(job);
        return modelMapper.map(savedJob, JobResponse.class);
    }

    @Override
    public List<JobResponse> getAllJobs() {
        List<Job>jobs = jobRepository.findAll();

        return jobs.stream().map(job -> modelMapper.map(job, JobResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse getJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Not Found: " +id));
        return modelMapper.map(job, JobResponse.class);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public List<JobResponse> getJobsByCategory(String category) {
        List<Job>jobsCategory = jobRepository.findByCategory(category);

        return jobsCategory.stream().map(
                job -> modelMapper.map(job, JobResponse.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> getJobsBySalaryRange(Integer minSalary, Integer maxSalary) {
        return jobRepository.findBySalaryMinGreaterThanEqualAndSalaryMaxLessThanEqual(minSalary, maxSalary).stream()
                .map(job -> modelMapper.map(job, JobResponse.class))
                .collect(Collectors.toList());
    }
}
