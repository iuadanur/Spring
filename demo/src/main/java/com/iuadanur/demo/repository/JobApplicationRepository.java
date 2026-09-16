package com.iuadanur.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iuadanur.demo.model.JobApplication;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {
}