package com.example.mahira2;

public class login
{
    String message ,mob,firstname,lastname,role;

    public login(String message, String mob, String firstname, String lastname, String role) {
        this.message = message;
        this.mob = mob;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

