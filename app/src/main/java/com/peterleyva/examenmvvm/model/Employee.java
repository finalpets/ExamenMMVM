package com.peterleyva.examenmvvm.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "employee_table",foreignKeys = @ForeignKey(entity = Sucursal.class,parentColumns = "id",childColumns = "sucursal_id"))
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "sucursal_id")
    private int sucursal_id;

    String name;

    String rfc;

    String puesto;

    public Employee(int sucursal_id, String name, String rfc, String puesto) {
        this.sucursal_id = sucursal_id;
        this.name = name;
        this.rfc = rfc;
        this.puesto = puesto;
    }

    public int getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(int sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
