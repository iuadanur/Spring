package com.iuadanur.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.iuadanur.demo.dto.JobApplicationRequest;
import com.iuadanur.demo.dto.JobApplicationResponse;
import com.iuadanur.demo.model.JobApplication;
import com.iuadanur.demo.service.JobApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping
    public List<JobApplicationResponse> getAllApplications() {
        return jobApplicationService
                .getAllApplications()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> getApplicationById(
            @PathVariable Long id) {

        JobApplication application =
                jobApplicationService.getApplicationById(id);

        if (application == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toResponse(application));
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponse> addApplication(
            @Valid @RequestBody JobApplicationRequest request) {

        JobApplication application = new JobApplication();

        application.setCompany(request.getCompany());
        application.setPosition(request.getPosition());
        application.setStatus(request.getStatus());

        JobApplication created =
                jobApplicationService.addApplication(application);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody JobApplicationRequest request) {

        JobApplication application = new JobApplication();

        application.setCompany(request.getCompany());
        application.setPosition(request.getPosition());
        application.setStatus(request.getStatus());

        JobApplication updated =
                jobApplicationService.updateApplication(id, application);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {

        boolean deleted =
                jobApplicationService.deleteApplication(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    private JobApplicationResponse toResponse(JobApplication application) {
        return new JobApplicationResponse(
                application.getId(),
                application.getCompany(),
                application.getPosition(),
                application.getStatus()
        );
    }
}