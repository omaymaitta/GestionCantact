package com.example.annuaire2.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {
    @Query("SELECT * FROM contact")
    List<Contact> getAllContacts();
    @Insert
    void insertcontact(Contact... contacts);
    @Delete
    void delete(Contact contact);
    @Update
    void update(Contact contact);
    @Query("SELECT *  FROM contact where id=:id")
    public Contact getContact (Long id);
}
