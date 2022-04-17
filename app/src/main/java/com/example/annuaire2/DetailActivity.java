package com.example.annuaire2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.annuaire2.db.Appdatabase;
import com.example.annuaire2.db.Contact;
import com.example.annuaire2.db.ContactDAO;

public class DetailActivity extends AppCompatActivity {
    EditText t1 ;
    EditText t2 ;
    EditText t3;
    EditText t4 ;
    EditText t5 ;
ImageButton mod;
    private Appdatabase appDataBase;
    ContactDAO contactDAO;
String idItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
      idItem=getIntent().getStringExtra("info");

        t1=findViewById(R.id.firstNameU);
        t2=findViewById(R.id.lastNameU);
        t3=findViewById(R.id.jobU);
        t4=findViewById(R.id.phoneU);
        t5=findViewById(R.id.emailU);
        mod=findViewById(R.id.saveButton2);

       new Thread(()->{
           accessDB();
            Contact c= contactDAO.getContact(Long.parseLong(idItem));
            runOnUiThread(()-> {
              t1.setText(c.nom);
                t2.setText(c.prenom);
                t3.setText(c.job);
                t4.setText(c.phone);
                t5.setText(c.email);
            });
        }).start();
mod.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        new Thread(()->{
          Contact cn=new Contact();

          cn.setNom(t2.getText().toString());
          cn.setPrenom(t1.getText().toString());
          cn.setJob(t3.getText().toString());
          cn.setPhone(t4.getText().toString());
          cn.setEmail(t5.getText().toString());
            accessDB();
            contactDAO.update(cn);
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(intent);
        }).start();
    }
});


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