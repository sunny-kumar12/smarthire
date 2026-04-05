package com.sunny.smarthire.repository;

import com.sunny.smarthire.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByLocation(String location);
    List<Job> findByJobType(Job.JobType jobType);
    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findBySkillsContainingIgnoreCase(String skill);
}