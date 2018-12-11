package com.peterleyva.examenmvvm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String email;
    private String rfc;
    private String password;
    private String company_name;
    private String password_confirmation;


    public User(String name, String email, String rfc, String password, String company_name, String password_confirmation) {
        this.name = name;
        this.email = email;
        this.rfc = rfc;
        this.password = password;
        this.company_name = company_name;
        this.password_confirmation = password_confirmation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRfc() {
        return rfc;
    }

    public String getPassword() {
        return password;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }
}
