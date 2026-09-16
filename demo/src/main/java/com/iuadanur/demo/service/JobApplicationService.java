package com.iuadanur.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iuadanur.demo.model.JobApplication;
import com.iuadanur.demo.repository.JobApplicationRepository;

@Service 
public class JobApplicationService {
    
    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    public JobApplication getApplicationById(Long id) {
        return jobApplicationRepository.findById(id).orElse(null);
    }

    public JobApplication addApplication(JobApplication application) {
        return jobApplicationRepository.save(application);
    }

    public JobApplication updateApplication(Long id, JobApplication updatedApplication) {

        JobApplication application =
                jobApplicationRepository.findById(id).orElse(null);

        if (application == null) {
            return null;
        }

        application.setCompany(updatedApplication.getCompany());
        application.setPosition(updatedApplication.getPosition());
        application.setStatus(updatedApplication.getStatus());

        return jobApplicationRepository.save(application);
    }

    public boolean deleteApplication(Long id) {

        if (!jobApplicationRepository.existsById(id)) {
            return false;
        }

        jobApplicationRepository.deleteById(id);
        return true;
    }
}
