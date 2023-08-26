package com.SahanDilshan.TheJobs.models;

public class RegistrationDTO {
    private String username;
    private String password;

    private String personName;
    private String phone;


    public RegistrationDTO(){
        super();
    }



    public RegistrationDTO(String username, String password, String personName, String phone) {
        this.username = username;
        this.password = password;
        this.personName = personName;
        this.phone = phone;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
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

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", personName='" + personName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
