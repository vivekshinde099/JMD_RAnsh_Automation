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
import com.jio.ranshjmd.Ticket_status;

public class CommentAddattachmentadapter extends RecyclerView.Adapter<CommentAddattachmentadapter.attachmentholder>  {

    private ArrayList<Myattachmentlist> amyattachmentlists;
    Myattachmentlist[] myattachmentlists;
    Context context;

    public CommentAddattachmentadapter(ArrayList<Myattachmentlist> myattachmentlists, Ticket_status sr_form) {
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
        return amyattachmentlists.size();
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
