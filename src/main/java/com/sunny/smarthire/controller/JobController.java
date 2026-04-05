package com.sunny.smarthire.controller;

import com.sunny.smarthire.dto.ApiResponse;
import com.sunny.smarthire.dto.JobRequest;
import com.sunny.smarthire.entity.Job;
import com.sunny.smarthire.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String title) {
        return ResponseEntity.ok(jobService.searchByTitle(title));
    }

    @GetMapping("/filter/location")
    public ResponseEntity<List<Job>> filterByLocation(@RequestParam String location) {
        return ResponseEntity.ok(jobService.filterByLocation(location));
    }

    @GetMapping("/filter/type")
    public ResponseEntity<List<Job>> filterByType(@RequestParam Job.JobType jobType) {
        return ResponseEntity.ok(jobService.filterByType(jobType));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('RECRUITER') or hasRole('ADMIN')")
    public ResponseEntity<Job> createJob(@RequestBody JobRequest request,
                                         Principal principal) {
        return ResponseEntity.ok(jobService.createJob(request, principal.getName()));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('RECRUITER') or hasRole('ADMIN')")
    public ResponseEntity<Job> updateJob(@PathVariable Long id,
                                         @RequestBody JobRequest request) {
        return ResponseEntity.ok(jobService.updateJob(id, request));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok(new ApiResponse(true, "Job deleted successfully", null));
    }
}