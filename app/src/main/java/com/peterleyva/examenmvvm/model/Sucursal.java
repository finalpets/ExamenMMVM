package com.peterleyva.examenmvvm.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sucursal_table")
public class Sucursal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String adress;

    private String colonial;

    private int number;

    private int postal_codel;

    private String city;

    private String country;

    public Sucursal(String name, String adress, String colonial, int number, int postal_codel, String city, String country) {
        this.name = name;
        this.adress = adress;
        this.colonial = colonial;
        this.number = number;
        this.postal_codel = postal_codel;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getColonial() {
        return colonial;
    }

    public int getNumber() {
        return number;
    }

    public int getPostal_codel() {
        return postal_codel;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
