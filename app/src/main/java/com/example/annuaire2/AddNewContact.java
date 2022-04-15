package com.example.annuaire2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.annuaire2.db.Appdatabase;
import com.example.annuaire2.db.Contact;

public class AddNewContact extends AppCompatActivity {
EditText t1 ;
EditText t2 ;
EditText t3;
EditText t4 ;
EditText t5 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        t1=findViewById(R.id.firstName);
        t2=findViewById(R.id.lastName);
        t3=findViewById(R.id.job);
        t4=findViewById(R.id.phone);
        t5=findViewById(R.id.email);
    }
    public void vider(View view){
     t1.setText("");
     t2.setText("");
     t3.setText("");
     t4.setText("");
     t5.setText("");
    }
    public void saveContact(View view){
        Appdatabase db = Appdatabase.getDbInstance(this.getApplicationContext());
        Contact contact = new Contact();
       contact.nom= t1.getText().toString();
       contact.prenom= t2.getText().toString();
       contact.job= t3.getText().toString();
       contact.phone= t4.getText().toString();
       contact.email= t5.getText().toString();
        db.contactDAO().insertcontact(contact);
       finish();
    }
}