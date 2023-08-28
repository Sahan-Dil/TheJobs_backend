package com.SahanDilshan.TheJobs.models;

import jakarta.persistence.*;

@Entity
@Table(name = "consultancy")
public class Consultancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "consultancy_id")
    private Integer consultancyId;

    private String name;
    private String country;
    private String gender;
    private String description;
    private String phone;
    private String email;
    @Column(unique = true)
    private Integer userId;


    // Constructors, getters, and setters

    public Consultancy() {
    }

    public Consultancy(Integer consultancyId, String name, String country, String gender, String description, String phone, String email, Integer userId) {
        this.consultancyId = consultancyId;
        this.name = name;
        this.country = country;
        this.gender = gender;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.userId = userId;
    }

    public Integer getConsultancyId() {
        return consultancyId;
    }

    public void setConsultancyId(Integer consultancyId) {
        this.consultancyId = consultancyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
