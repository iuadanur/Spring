package com.iuadanur.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.iuadanur.demo.exception.ResourceNotFoundException;
import com.iuadanur.demo.model.ApplicationStatus;
import com.iuadanur.demo.model.JobApplication;
import com.iuadanur.demo.service.JobApplicationService;

@WebMvcTest(JobApplicationController.class)
class JobApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JobApplicationService jobApplicationService;

    @Test
    void shouldReturnApplicationById() throws Exception {

        JobApplication application = new JobApplication(
                1L,
                "Google",
                "Backend Developer",
                ApplicationStatus.INTERVIEW
        );

        when(jobApplicationService.getApplicationById(1L))
                .thenReturn(application);

        mockMvc.perform(get("/applications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.company").value("Google"))
                .andExpect(jsonPath("$.position").value("Backend Developer"))
                .andExpect(jsonPath("$.status").value("INTERVIEW"));
    }

    @Test
    void shouldReturn404WhenApplicationDoesNotExist() throws Exception {

        when(jobApplicationService.getApplicationById(99L))
                .thenThrow(
                        new ResourceNotFoundException(
                                "Job application not found with id: 99"
                        )
                );

        mockMvc.perform(get("/applications/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message")
                        .value("Job application not found with id: 99"));
    }

    @Test
    void shouldCreateApplication() throws Exception {

        JobApplication savedApplication = new JobApplication(
                1L,
                "Amazon",
                "Software Engineer",
                ApplicationStatus.APPLIED
        );

        when(jobApplicationService.addApplication(any(JobApplication.class)))
                .thenReturn(savedApplication);

        mockMvc.perform(post("/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "company": "Amazon",
                                  "position": "Software Engineer",
                                  "status": "APPLIED"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.company").value("Amazon"))
                .andExpect(jsonPath("$.status").value("APPLIED"));
    }

    @Test
    void shouldReturn400ForInvalidApplication() throws Exception {

        mockMvc.perform(post("/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "company": "",
                                  "position": "",
                                  "status": null
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.company")
                        .value("Company is required"))
                .andExpect(jsonPath("$.position")
                        .value("Position is required"))
                .andExpect(jsonPath("$.status")
                        .value("Status is required"));
    }
}