package com.vaadin.example.data.entity;

public class User {

    private Long benutzerId;
    private String name;
    private String nachname;
    private String username;
    private String passwort;
    private String recht;

    public User() {

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
