package com;

public class TestModel {

    String name;
    String email;
    String job;
    String Phone;

    public TestModel() {
    }

    public TestModel(String name, String email, String job, String phone) {
        this.name = name;
        this.email = email;
        this.job = job;
        Phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
