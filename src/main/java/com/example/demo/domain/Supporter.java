/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author iset1enloc
 */
@Entity
@Table(name = "supporter")
public class Supporter extends User {
    private String title;

    public Supporter(){
        
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Supporter(String title) {
        this.title = title;
    }
    
    public Supporter(String username, String email, String password, String fullName, String address, String phone,
            String title) {
        super(username, email, password, fullName, address, phone);
        this.title = title;
    }

    @Override
    public String toString() {
        return super.toString()+"Supporter [title=" + title + "]";
    }

    
}
