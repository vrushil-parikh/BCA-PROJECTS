package com.example.livepractice;

public class UserInfo {
   String email;
   String password;
    String  username;
    String userType;
    public UserInfo(String email, String password,String username,String userTye) {
        this.email = email;
        this.password = password;
        this.username=username;
        this.userType=userTye;
    }
    UserInfo(){

   }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
