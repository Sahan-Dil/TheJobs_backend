package com.SahanDilshan.TheJobs.models;

import java.util.Set;

// Create a DTO class to represent user details
public class UserDetailsDTO {
    private Integer userId;
    private String username;
    private String personName;
    private String phone;
    private Set<Role> authorities;

    // Constructors, getters, and setters

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(Integer userId, String username, String personName, String phone, Set<Role> authorities) {
        this.userId = userId;
        this.username = username;
        this.personName = personName;
        this.phone = phone;
        this.authorities = authorities;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
