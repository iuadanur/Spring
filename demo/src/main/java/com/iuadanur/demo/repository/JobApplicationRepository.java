package com.iuadanur.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iuadanur.demo.model.ApplicationStatus;
import com.iuadanur.demo.model.JobApplication;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByStatus(ApplicationStatus status);

    List<JobApplication> findByCompanyContainingIgnoreCase(String company);
}