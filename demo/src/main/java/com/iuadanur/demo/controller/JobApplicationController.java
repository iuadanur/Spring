package com.iuadanur.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.iuadanur.demo.model.JobApplication;
import com.iuadanur.demo.service.JobApplicationService;

import java.util.List;

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
    public JobApplication addApplication(@RequestBody JobApplication application) {
        return jobApplicationService.addApplication(application);
    }
    @GetMapping("/{id}")
    public JobApplication getApplicationById(@PathVariable Long id) {
        return jobApplicationService.getApplicationById(id);
    }
    @PutMapping("/{id}")
    public JobApplication updateApplication(
            @PathVariable Long id,
            @RequestBody JobApplication application) {
            
        return jobApplicationService.updateApplication(id, application);
    }
}
