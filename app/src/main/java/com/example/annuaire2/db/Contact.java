package com.example.annuaire2.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
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

}
