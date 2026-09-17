package com.iuadanur.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iuadanur.demo.exception.ResourceNotFoundException;
import com.iuadanur.demo.model.ApplicationStatus;
import com.iuadanur.demo.model.JobApplication;
import com.iuadanur.demo.repository.JobApplicationRepository;

@ExtendWith(MockitoExtension.class)
class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @InjectMocks
    private JobApplicationService jobApplicationService;

    @Test
    void shouldReturnAllApplications() {

        JobApplication application1 = new JobApplication(
                1L,
                "Google",
                "Backend Developer",
                ApplicationStatus.APPLIED
        );

        JobApplication application2 = new JobApplication(
                2L,
                "Amazon",
                "Software Engineer",
                ApplicationStatus.INTERVIEW
        );

        when(jobApplicationRepository.findAll())
                .thenReturn(List.of(application1, application2));

        List<JobApplication> result =
                jobApplicationService.getAllApplications();

        assertEquals(2, result.size());
        assertEquals("Google", result.get(0).getCompany());

        verify(jobApplicationRepository).findAll();
    }

    @Test
    void shouldReturnApplicationById() {

        JobApplication application = new JobApplication(
                1L,
                "Google",
                "Backend Developer",
                ApplicationStatus.APPLIED
        );

        when(jobApplicationRepository.findById(1L))
                .thenReturn(Optional.of(application));

        JobApplication result =
                jobApplicationService.getApplicationById(1L);

        assertEquals("Google", result.getCompany());
        assertEquals(ApplicationStatus.APPLIED, result.getStatus());

        verify(jobApplicationRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenApplicationDoesNotExist() {

        when(jobApplicationRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> jobApplicationService.getApplicationById(99L)
        );
    }

    @Test
    void shouldAddApplication() {

        JobApplication application = new JobApplication(
                null,
                "Microsoft",
                "Cloud Engineer",
                ApplicationStatus.APPLIED
        );

        JobApplication savedApplication = new JobApplication(
                1L,
                "Microsoft",
                "Cloud Engineer",
                ApplicationStatus.APPLIED
        );

        when(jobApplicationRepository.save(application))
                .thenReturn(savedApplication);

        JobApplication result =
                jobApplicationService.addApplication(application);

        assertEquals(1L, result.getId());
        assertEquals("Microsoft", result.getCompany());

        verify(jobApplicationRepository).save(application);
    }

    @Test
    void shouldUpdateApplication() {

        JobApplication existingApplication = new JobApplication(
                1L,
                "Google",
                "Backend Developer",
                ApplicationStatus.APPLIED
        );

        JobApplication updatedData = new JobApplication(
                null,
                "Google",
                "Senior Backend Developer",
                ApplicationStatus.INTERVIEW
        );

        when(jobApplicationRepository.findById(1L))
                .thenReturn(Optional.of(existingApplication));

        when(jobApplicationRepository.save(existingApplication))
                .thenReturn(existingApplication);

        JobApplication result =
                jobApplicationService.updateApplication(1L, updatedData);

        assertEquals("Senior Backend Developer", result.getPosition());
        assertEquals(ApplicationStatus.INTERVIEW, result.getStatus());

        verify(jobApplicationRepository).save(existingApplication);
    }

    @Test
    void shouldDeleteApplication() {

        JobApplication application = new JobApplication(
                1L,
                "Google",
                "Backend Developer",
                ApplicationStatus.APPLIED
        );

        when(jobApplicationRepository.findById(1L))
                .thenReturn(Optional.of(application));

        jobApplicationService.deleteApplication(1L);

        verify(jobApplicationRepository).delete(application);
    }
}