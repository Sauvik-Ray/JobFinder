package com.JobFinder.JobFinder.Repository;

import com.JobFinder.JobFinder.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByGuid(String guid);

    List<Job> findByCategory(String category);

    List<Job> findBySalaryMinGreaterThanEqualAndSalaryMaxLessThanEqual(Integer minSalary, Integer maxSalary);
}
