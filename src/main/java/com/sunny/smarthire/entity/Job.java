package com.sunny.smarthire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String location;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String skills;

    @Column(nullable = false)
    private String salary;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Column(nullable = false)
    private LocalDateTime postedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private User recruiter;

    public enum JobType {
        FULL_TIME, PART_TIME, INTERNSHIP, REMOTE
    }
}