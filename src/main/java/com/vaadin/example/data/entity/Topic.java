package com.vaadin.example.data.entity;

import java.util.List;

public class Topic {

    private Long topicId;
    private String name;
    private List<Grade> grades ;

    public Topic() {
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



}
