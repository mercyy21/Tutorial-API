package com.example.demo.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "tutorial")
public class TutorialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutorial_id", nullable = false)
    private Integer tutorialId;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "published status", nullable = false)
    private boolean publishedStatus;

    //Getters

    public Integer getId() {
        return tutorialId;
    }
    public String getTitle(){
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPublishedStatus() {
        return publishedStatus;
    }

    //Setters

    public void setId(Integer tutorialId) {
        this.tutorialId = tutorialId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishedStatus(boolean publishedStatus) {
        this.publishedStatus = publishedStatus;
    }
}
