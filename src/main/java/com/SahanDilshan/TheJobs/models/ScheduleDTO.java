package com.SahanDilshan.TheJobs.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDTO {

    private String date;
    private String time;
    private String userName;
    private Integer userId;
    private Integer consultantId;

    public ScheduleDTO() {
    }

    public ScheduleDTO(String date, String time, String userName, Integer userId, Integer consultantId) {
        this.date = date;
        this.time = time;
        this.userName = userName;
        this.userId = userId;
        this.consultantId = consultantId;
    }

// Getters and setters

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
