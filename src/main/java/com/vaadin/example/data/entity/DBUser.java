package com.vaadin.example.data.entity;

import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.List;

public class DBUser {

    private Long benutzerId;
    private String name;
    private String nachname;
    private String username;
    private String passwort;
    private String recht;
    private List<Topic> topics;

    public DBUser() {

    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Long getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(Long benutzerId) {
        this.benutzerId = benutzerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getRecht() {
        return recht;
    }

    public void setRecht(String recht) {
        this.recht = recht;
    }

}