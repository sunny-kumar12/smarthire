package com.sunny.smarthire.controller;

import com.sunny.smarthire.entity.Application;
import com.sunny.smarthire.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply/{jobId}")
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Application> applyForJob(@PathVariable Long jobId,
                                                   Principal principal) {
        return ResponseEntity.ok(
                applicationService.applyForJob(jobId, principal.getName()));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<List<Application>> getMyApplications(Principal principal) {
        return ResponseEntity.ok(
                applicationService.getMyApplications(principal.getName()));
    }

    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasRole('RECRUITER') or hasRole('ADMIN')")
    public ResponseEntity<List<Application>> getApplicationsForJob(
            @PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getApplicationsForJob(jobId));
    }

    @PutMapping("/status/{applicationId}")
    @PreAuthorize("hasRole('RECRUITER') or hasRole('ADMIN')")
    public ResponseEntity<Application> updateStatus(
            @PathVariable Long applicationId,
            @RequestParam Application.Status status) {
        return ResponseEntity.ok(applicationService.updateStatus(applicationId, status));
    }
}