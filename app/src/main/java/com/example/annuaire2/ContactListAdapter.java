package com.example.annuaire2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.annuaire2.db.Appdatabase;
import com.example.annuaire2.db.Contact;

import java.io.Serializable;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter <ContactListAdapter.MyViewHolder>{
    private RecyclerViewInterface recyclerViewInterface;
private Context context;
private List<Contact> contactList;
    private Appdatabase database;
    RecyclerView rec;
    public ContactListAdapter(Context context,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.recyclerViewInterface=recyclerViewInterface;
        notifyDataSetChanged();
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerow,parent,false);
        return new MyViewHolder(view,recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.MyViewHolder holder, int position) {
        holder.id.setText(this.contactList.get(position).id.toString());
        holder.FirstName.setText(this.contactList.get(position).prenom);
        holder.LastName.setText(this.contactList.get(position).nom);
        holder.job.setText(this.contactList.get(position).job);
        holder.phone.setText(this.contactList.get(position).phone);
        holder.email.setText(this.contactList.get(position).email);
       rec.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Contact d=contactList.get(holder.getAdapterPosition());
                        database.contactDAO().delete(d);
                        contactList.clear();
                        contactList.addAll(database.contactDAO().getAllContacts());
                        notifyDataSetChanged();
                        Toast toast=new Toast(context);
                        toast.makeText(context,"the contact was successfully deleted",Toast.LENGTH_SHORT).show();
                    }
                });
                /*builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("confirmation");
                alertDialog.setMessage("are you sure you want to delete this");
                alertDialog.show();*/

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.contactList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView FirstName;
        TextView LastName;
        TextView job;
        TextView phone;
        TextView email;
        TextView id;
        public MyViewHolder(View view,RecyclerViewInterface recyclerViewInterface) {
            super(view);
            id=view.findViewById(R.id.id);
            FirstName = view.findViewById(R.id.firstN);
            LastName = view.findViewById(R.id.lastN);
            job = view.findViewById(R.id.jobT);
            phone = view.findViewById(R.id.phoneT);
            email = view.findViewById(R.id.emailT);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null){
                        int pos= getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.OnItemClick(pos);

                        }
                    }
                }
            });

}

    }
}
