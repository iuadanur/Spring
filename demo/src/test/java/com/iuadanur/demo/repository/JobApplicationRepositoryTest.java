package com.iuadanur.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.iuadanur.demo.model.ApplicationStatus;
import com.iuadanur.demo.model.JobApplication;

@DataJpaTest
class JobApplicationRepositoryTest {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @BeforeEach
    void setUp() {

        JobApplication google = new JobApplication(
                null,
                "Google",
                "Backend Developer",
                ApplicationStatus.INTERVIEW
        );

        JobApplication amazon = new JobApplication(
                null,
                "Amazon",
                "Software Engineer",
                ApplicationStatus.APPLIED
        );

        JobApplication googleCloud = new JobApplication(
                null,
                "Google Cloud",
                "Platform Engineer",
                ApplicationStatus.APPLIED
        );

        jobApplicationRepository.saveAll(
                List.of(google, amazon, googleCloud)
        );
    }

    @Test
    void shouldFindApplicationsByStatus() {

        List<JobApplication> result =
                jobApplicationRepository.findByStatus(
                        ApplicationStatus.APPLIED
                );

        assertEquals(2, result.size());
    }

    @Test
    void shouldFindApplicationsByCompanyIgnoringCase() {

        List<JobApplication> result =
                jobApplicationRepository
                        .findByCompanyContainingIgnoreCase("GOOGLE");

        assertEquals(2, result.size());
    }

    @Test
    void shouldFindApplicationsByStatusAndCompany() {

        List<JobApplication> result =
                jobApplicationRepository
                        .findByStatusAndCompanyContainingIgnoreCase(
                                ApplicationStatus.INTERVIEW,
                                "google"
                        );

        assertEquals(1, result.size());
        assertEquals("Google", result.get(0).getCompany());
    }
}