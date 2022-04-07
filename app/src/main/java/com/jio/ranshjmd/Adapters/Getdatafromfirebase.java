package com.jio.ranshjmd.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import com.jio.ranshjmd.ApiConstructor.TicketListConstructor;
import com.jio.ranshjmd.Bottomnavigationbar.TicketFragment;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.Ticket_status;

public class Getdatafromfirebase extends RecyclerView.Adapter<Getdatafromfirebase.dataholder> {

    List<TicketListConstructor> listmygetticketdata;
    TicketFragment context;
    private final int limit = 5;

    public Getdatafromfirebase(List<TicketListConstructor> mygetticketdata, TicketFragment accounthistory) {
        this.listmygetticketdata = mygetticketdata;
        this.context = accounthistory;
    }

    @NonNull
    @NotNull
    @Override
    public dataholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.accounthistory_layout,parent,false);
        dataholder dh = new dataholder(view);
        return dh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull dataholder holder, int position) {

        TicketListConstructor mygetticketdata = listmygetticketdata.get(position);
        holder.text.setText("BT"+mygetticketdata.getIntid());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Ticket_status.class);
                intent.putExtra("Account_history_BTNO",mygetticketdata.getIntid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        if(listmygetticketdata.size() > limit){
            return limit;
        }
        else
        {
            return listmygetticketdata.size();
        }
    }

    public class dataholder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView text,text1;
        CardView cardView;
        public dataholder(@NonNull @NotNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.TICKETTAG);

            cardView = itemView.findViewById(R.id.TICKETTAGcardyaout);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
