package com.project.app.Requests;

import com.project.app.Enums.Roles;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateNaissance;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
