package com.sunny.smarthire.dto;

import com.sunny.smarthire.entity.Job;
import lombok.Data;

@Data
public class JobRequest {
    private String title;
    private String company;
    private String location;
    private String description;
    private String skills;
    private String salary;
    private Job.JobType jobType;
}