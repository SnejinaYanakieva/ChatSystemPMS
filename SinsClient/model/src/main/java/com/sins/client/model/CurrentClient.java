package com.sins.client.model;

import java.util.List;

public class CurrentClient extends Person{
    
    private String nickName;
    private String email;
    private String phone;
    private String city;

    public String getNickname() {
        return nickName;
    }

    public void setNickname(String nickname) {
        this.nickName = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public List<String> validateItem() {
        List<String> errors = null;
        return errors;
    }
    
}
