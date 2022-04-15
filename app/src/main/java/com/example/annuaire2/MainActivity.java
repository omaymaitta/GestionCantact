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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.annuaire2.db.Appdatabase;
import com.example.annuaire2.db.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{
   RecyclerView rec;
   TextView t;
private ContactListAdapter contactListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        loadContactList();
        rec=findViewById(R.id.rec);
        t=findViewById(R.id.firstN);

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

      //  intent.putExtra("f",t.getText().toString());

        startActivity(intent);
    }

}