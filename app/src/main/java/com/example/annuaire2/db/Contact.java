package com.example.annuaire2.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @ColumnInfo(name="id" )
    @PrimaryKey(autoGenerate = true)
    public Long id;
    @ColumnInfo(name="nom" )
    public String nom;
    @ColumnInfo(name="prenom")
    public String prenom;
    @ColumnInfo(name="job")
    public String job;
    @ColumnInfo(name="phone")
    public String phone;
    @ColumnInfo(name="email")
    public String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
