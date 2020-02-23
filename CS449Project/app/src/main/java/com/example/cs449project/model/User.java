package com.example.cs449project.model;

public class User {
    private String acc_id;
    private String acc_Firstname;
    private String acc_Lastname;
    private String acc_imageURL;
    private String acc_phonenumber;
    private String acc_search;
    private String acc_status;

    public User(String acc_id, String acc_search, String acc_Firstname, String acc_Lastname, String acc_imageURL, String acc_phonenumber, String acc_status) {
        this.acc_id = acc_id;
        this.acc_Firstname = acc_Firstname;
        this.acc_Lastname = acc_Lastname;
        this.acc_imageURL = acc_imageURL;
        this.acc_phonenumber = acc_phonenumber;
        this.acc_search = acc_search;
        this.acc_status = acc_status;

    }

    public User() {
    }
    public String getPhonenumber(){return acc_phonenumber;}
    public void setPhonenumber(String acc_phonenumber){
        this.acc_phonenumber = acc_phonenumber;
    }
    public String getId() {
        return acc_id;
    }

    public void setId(String stu_id) {
        this.acc_id = stu_id;
    }

    public String getFirstname() {
        return acc_Firstname;
    }

    public void setFirstname(String Firstname) {
        this.acc_Firstname = Firstname;
    }

    public String getLastname() {
        return acc_Lastname;
    }

    public void setLastname(String stu_Lastname) {
        this.acc_Lastname = stu_Lastname;
    }

    public String getImageURL() {
        return acc_imageURL;
    }

    public void setImageURL(String stu_imageURL) {
        this.acc_imageURL = stu_imageURL;
    }

    public String getStu_search() {
        return acc_search;
    }

    public void setStu_search(String stu_search) {
        this.acc_search = stu_search;
    }

    public String getStu_status() {
        return acc_status;
    }

    public void setStu_status(String stu_status) {
        this.acc_status = stu_status;
    }
}

