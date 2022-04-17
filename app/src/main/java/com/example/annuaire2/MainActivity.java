package com.example.annuaire2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.annuaire2.db.Appdatabase;
import com.example.annuaire2.db.Contact;
import com.example.annuaire2.db.ContactDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{
   RecyclerView rec;
    TextView t1 ;
    TextView t2 ;
    TextView t3;
    TextView t4 ;
    TextView t5 ;
    private Appdatabase appDataBase;
    ContactDAO contactDAO;

private ContactListAdapter contactListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        loadContactList();
        rec=findViewById(R.id.rec);
        t1=findViewById(R.id.firstN);
        t2=findViewById(R.id.lastN);
        t3=findViewById(R.id.jobT);
        t4=findViewById(R.id.phoneT);
        t5=findViewById(R.id.emailT);
       rec.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new Thread(()->{
                    Contact cn=new Contact();
                    cn.setNom(t2.getText().toString());
                    cn.setPrenom(t1.getText().toString());
                    cn.setJob(t3.getText().toString());
                    cn.setPhone(t4.getText().toString());
                    cn.setEmail(t5.getText().toString());
                    accessDB();
                    contactDAO.delete(cn);
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }).start();
                return false;
            }
        });

    }
    public void accessDB(){
        appDataBase= Appdatabase.getDbInstance(this.getApplicationContext());
        contactDAO = appDataBase.contactDAO();
    }
    public void add(View view){
        Intent intent = new Intent(MainActivity.this, AddNewContact.class);
        startActivity(intent);
    }
    private void initRecyclerView() {
        RecyclerView recyclerView =findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        contactListAdapter=new ContactListAdapter(this,this);
        recyclerView.setAdapter(contactListAdapter);

}
  private void loadContactList() {
        Appdatabase db = Appdatabase.getDbInstance(this.getApplicationContext());
        List<Contact> contactList = db.contactDAO().getAllContacts();
        contactListAdapter.setContactList(contactList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadContactList() ;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        TextView t;
        t=findViewById(R.id.id);
        intent.putExtra("info",t.getText().toString());
        startActivity(intent);
    }


}