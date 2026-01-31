package com.brainmentors.resumescoreapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

public class ResumeCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 30, nullable = false, unique = true)
    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String resumeText;
    @Column(columnDefinition = "LONGTEXT")
    private String jdText;
    @Column(columnDefinition = "LONGTEXT")
    private String missingWords;
    private int score;
    private String shortListStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public String getJdText() {
        return jdText;
    }

    public void setJdText(String jdText) {
        this.jdText = jdText;
    }

    public String getMissingWords() {
        return missingWords;
    }

    public void setMissingWords(String missingWords) {
        this.missingWords = missingWords;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getShortListStatus() {
        return shortListStatus;
    }

    public void setShortListStatus(String shortListStatus) {
        this.shortListStatus = shortListStatus;
    }

}
