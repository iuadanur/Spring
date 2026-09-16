package com.iuadanur.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.iuadanur.demo.model.JobApplication;
import com.iuadanur.demo.service.JobApplicationService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController 
@RequestMapping("/applications")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
    this.jobApplicationService = jobApplicationService;
    }
    @GetMapping
    public List<JobApplication> getAllApplications() {
        return jobApplicationService.getAllApplications();
    }
    @PostMapping
    public ResponseEntity<JobApplication> addApplication(
            @RequestBody JobApplication application) {

        JobApplication createdApplication =
                jobApplicationService.addApplication(application);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdApplication);
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getApplicationById(@PathVariable Long id) {
        JobApplication application = jobApplicationService.getApplicationById(id);

        if (application == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(application);
    }
    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateApplication(
            @PathVariable Long id,
            @RequestBody JobApplication application) {

        JobApplication updatedApplication =
                jobApplicationService.updateApplication(id, application);

        if (updatedApplication == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedApplication);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {

        boolean deleted = jobApplicationService.deleteApplication(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
