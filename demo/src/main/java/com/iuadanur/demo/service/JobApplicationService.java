package com.iuadanur.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iuadanur.demo.exception.ResourceNotFoundException;
import com.iuadanur.demo.model.ApplicationStatus;
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
        return jobApplicationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job application not found with id: " + id
                        )
                );
    }

    public List<JobApplication> getApplicationsByStatus(ApplicationStatus status) {
        return jobApplicationRepository.findByStatus(status);
    }
    
    public JobApplication addApplication(JobApplication application) {
        return jobApplicationRepository.save(application);
    }

    public JobApplication updateApplication(
            Long id,
            JobApplication updatedApplication) {

        JobApplication application = getApplicationById(id);

        application.setCompany(updatedApplication.getCompany());
        application.setPosition(updatedApplication.getPosition());
        application.setStatus(updatedApplication.getStatus());

        return jobApplicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        JobApplication application = getApplicationById(id);

        jobApplicationRepository.delete(application);
    }
}