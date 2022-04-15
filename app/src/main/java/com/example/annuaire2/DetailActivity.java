package com.example.annuaire2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.annuaire2.db.Appdatabase;
import com.example.annuaire2.db.Contact;
import com.example.annuaire2.db.ContactDAO;

public class DetailActivity extends AppCompatActivity {
    EditText t1 ;
    EditText t2 ;
    EditText t3;
    EditText t4 ;
    EditText t5 ;

    private Appdatabase appDataBase;
    ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
       // Bundle ex=getIntent().getExtras();
        //String n=ex.getString("f");

        t1=findViewById(R.id.firstNameU);
        t2=findViewById(R.id.lastNameU);
        t3=findViewById(R.id.jobU);
        t4=findViewById(R.id.phoneU);
        t5=findViewById(R.id.emailU);

       /*new Thread(()->{
           accessDB();
            Contact c= contactDAO.getContact(n);
            runOnUiThread(()-> {
              t1.setText(c.nom);
                t2.setText(c.prenom);
                t3.setText(c.job);
                t4.setText(c.phone);
                t5.setText(c.email);
            });
        }).start();*/
    }
   public void accessDB(){
        appDataBase= Appdatabase.getDbInstance(this.getApplicationContext());
        contactDAO = appDataBase.contactDAO();
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