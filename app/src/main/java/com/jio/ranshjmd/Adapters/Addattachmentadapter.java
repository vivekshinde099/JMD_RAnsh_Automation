package com.jio.ranshjmd.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

import com.jio.ranshjmd.Models.Myattachmentlist;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.SR_Form;

public class Addattachmentadapter extends RecyclerView.Adapter<Addattachmentadapter.attachmentholder>  {

    private ArrayList<Myattachmentlist> amyattachmentlists;
    Myattachmentlist[] myattachmentlists;
    Context context;

    public Addattachmentadapter(ArrayList<Myattachmentlist> myattachmentlists, SR_Form sr_form) {
        this.amyattachmentlists = myattachmentlists;
        this.context = sr_form;
    }

    @NonNull
    @NotNull
    @Override
    public attachmentholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.attachmentlayout,parent,false);
        attachmentholder ah = new attachmentholder(view);
        return ah;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull attachmentholder holder, int position) {

        Myattachmentlist myattachmentlist = amyattachmentlists.get(position);
        holder.text.setText(myattachmentlist.getAttachmentpath());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amyattachmentlists.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {

        int limit = 3;
        return Math.min(amyattachmentlists.size(), limit);
         //   return amyattachmentlists.size();

    }

    public class attachmentholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView text;
        public attachmentholder(@NonNull  View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textattachment);
            imageView = itemView.findViewById(R.id.closeattachment);
        }

        @Override
        public void onClick(View v) {

        }
    }


}
