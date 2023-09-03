package com.SahanDilshan.TheJobs.models;

import jakarta.persistence.*;


import java.io.Serializable;

@Entity
@Table(name = "schedules")
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "consultant_id")
    private Integer consultantId;

    // Constructors, getters, and setters

    public Schedule() {
    }

    public Schedule(Integer scheduleId, String date, String time, String userName, Integer userId, Integer consultantId) {
        this.scheduleId = scheduleId;
        this.date = date;
        this.time = time;
        this.userName = userName;
        this.userId = userId;
        this.consultantId = consultantId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(Integer consultantId) {
        this.consultantId = consultantId;
    }
}
