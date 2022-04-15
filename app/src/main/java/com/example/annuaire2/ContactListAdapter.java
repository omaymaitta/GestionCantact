package com.example.annuaire2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.annuaire2.db.Contact;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter <ContactListAdapter.MyViewHolder>{
    private RecyclerViewInterface recyclerViewInterface;
private Context context;
private List<Contact> contactList;

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
        holder.FirstName.setText(this.contactList.get(position).prenom);
        holder.LastName.setText(this.contactList.get(position).nom);
        holder.job.setText(this.contactList.get(position).job);
        holder.phone.setText(this.contactList.get(position).phone);
        holder.email.setText(this.contactList.get(position).email);
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
        public MyViewHolder(View view,RecyclerViewInterface recyclerViewInterface) {
            super(view);
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
