package com.iuadanur.demo.dto;

import com.iuadanur.demo.model.ApplicationStatus;

public class JobApplicationResponse {

    private Long id;
    private String company;
    private String position;
    private ApplicationStatus status;

    public JobApplicationResponse(
            Long id,
            String company,
            String position,
            ApplicationStatus status) {

        this.id = id;
        this.company = company;
        this.position = position;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public ApplicationStatus getStatus() {
        return status;
    }
}