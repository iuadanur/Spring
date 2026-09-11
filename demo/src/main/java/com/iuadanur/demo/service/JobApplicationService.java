package com.iuadanur.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iuadanur.demo.model.JobApplication;

@Service 
public class JobApplicationService {
    
    private final List<JobApplication> applications = new ArrayList<>();

    public List<JobApplication> getAllApplications() {
        return applications;
    }

    public JobApplication addApplication(JobApplication application) {
        applications.add(application);
        return application;
    }
    public JobApplication getApplicationById(Long id) {
        for (JobApplication application : applications) {
            if (application.getId().equals(id)) {
                return application;
            }
        }

        return null;
    }
}
