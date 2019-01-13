package com.example.projectboard.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Sorry this field cannot be empty")
    private String summary;
    private String acceptanceCritteria;
    private String status;
    private String priority;

    public ProjectTask(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCritteria() {
        return acceptanceCritteria;
    }

    public void setAcceptanceCritteria(String acceptanceCritteria) {
        this.acceptanceCritteria = acceptanceCritteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority(){
        return priority;
    }
    public void setPriority(String priority){
        this.priority = priority;
    }
}
