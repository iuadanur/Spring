package com.iuadanur.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.iuadanur.demo.exception.ResourceNotFoundException;
import com.iuadanur.demo.model.ApplicationStatus;
import com.iuadanur.demo.model.JobApplication;
import com.iuadanur.demo.repository.JobApplicationRepository;

@Service
public class JobApplicationService {

    private static final Logger logger =
            LoggerFactory.getLogger(JobApplicationService.class);

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public List<JobApplication> getAllApplications() {
        logger.info("Fetching all job applications");

        return jobApplicationRepository.findAll();
    }

    public JobApplication getApplicationById(Long id) {
        logger.info("Fetching job application with id: {}", id);

        return jobApplicationRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Job application not found with id: {}", id);

                    return new ResourceNotFoundException(
                            "Job application not found with id: " + id
                    );
                });
    }

    public List<JobApplication> getApplicationsByStatus(
            ApplicationStatus status) {

        logger.info("Filtering applications by status: {}", status);

        return jobApplicationRepository.findByStatus(status);
    }

    public List<JobApplication> getApplicationsByStatusAndCompany(
            ApplicationStatus status,
            String company) {

        logger.info(
                "Filtering applications by status: {} and company: {}",
                status,
                company
        );

        return jobApplicationRepository
                .findByStatusAndCompanyContainingIgnoreCase(status, company);
    }

    public List<JobApplication> searchByCompany(String company) {
        logger.info("Searching applications by company: {}", company);

        return jobApplicationRepository
                .findByCompanyContainingIgnoreCase(company);
    }

    public JobApplication addApplication(JobApplication application) {

        JobApplication savedApplication =
                jobApplicationRepository.save(application);

        logger.info(
                "Created job application with id: {} for company: {}",
                savedApplication.getId(),
                savedApplication.getCompany()
        );

        return savedApplication;
    }

    public JobApplication updateApplication(
            Long id,
            JobApplication updatedApplication) {

        JobApplication application = getApplicationById(id);

        application.setCompany(updatedApplication.getCompany());
        application.setPosition(updatedApplication.getPosition());
        application.setStatus(updatedApplication.getStatus());

        JobApplication savedApplication =
                jobApplicationRepository.save(application);

        logger.info("Updated job application with id: {}", id);

        return savedApplication;
    }

    public void deleteApplication(Long id) {

        JobApplication application = getApplicationById(id);

        jobApplicationRepository.delete(application);

        logger.info("Deleted job application with id: {}", id);
    }
}