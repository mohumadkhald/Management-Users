package com.projects.facebook.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class User {
    @NotBlank(message = "First name cannot be empty or start space")
    @Pattern(regexp = "^[^0-9]{3,}$", message = "First name must be at least 3 characters long and cannot contain numbers")
    private String fname;

    @NotBlank(message = "Last name cannot be empty or start space")
    @Pattern(regexp = "^[^0-9]{3,}$", message = "Last Name must be at least 3 characters long and cannot contain numbers")
    private String lname;

    @NotBlank(message = "Phone cannot be empty or start space")
    @Pattern(regexp = "^01[0-2|5|9]\\d{8}$", message = "Phone number must be 11 digits and start with 01 followed by 0, 1, 2, 5, or 9")
    private String phone;

    @NotBlank(message = "Email cannot be empty or start space")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be empty or start space")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must contain at least one digit, one lowercase and one uppercase letter, and be at least 8 characters long")
    private String password;

    public User() {
    }

    public User( String fname, String lname, String phone, String email, String password) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

}
