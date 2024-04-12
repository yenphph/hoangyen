package com.example.buoi1;

public class user {
    String userid;
    String username;
    String useremail;
    String datnuoc;

    public user(String userid, String username, String useremail, String datnuoc) {
        this.userid = userid;
        this.username = username;
        this.useremail = useremail;
        this.datnuoc = datnuoc;
    }

    public user() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getDatnuoc() {
        return datnuoc;
    }

    public void setDatnuoc(String datnuoc) {
        this.datnuoc = datnuoc;
    }
}
