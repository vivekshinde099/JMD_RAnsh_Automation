package com.jio.ranshjmd.Adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import com.jio.ranshjmd.APICallBack;
import com.jio.ranshjmd.ApiConstructor.StatusticketdataConstructor;
import com.jio.ranshjmd.Bottomnavigationbar.Bellicon_activity;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.RequestAPI.CreateTicketPostdata;
import com.jio.ranshjmd.Ticket_status;

public class NotificationDataAdapter extends RecyclerView.Adapter<NotificationDataAdapter.notificationdataholder> {

    List<StatusticketdataConstructor> listnotifydata;
    Bellicon_activity context;
    String a = "";


    public NotificationDataAdapter(List<StatusticketdataConstructor> mygetticketdata, Bellicon_activity bellicon_activity) {
        this.listnotifydata = mygetticketdata;
        this.context = bellicon_activity;
    }

    @NonNull
    @NotNull
    @Override
    public notificationdataholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.notify_bellicon_list,parent,false);
        notificationdataholder dh = new notificationdataholder(view);
        return dh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull notificationdataholder holder, int position) {

        StatusticketdataConstructor statusticketdataConstructor = listnotifydata.get(position);

            holder.text.setText(statusticketdataConstructor.getStatus());
//            holder.text.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    a = String.valueOf(position);
//       //             Toast.makeText(context, a, Toast.LENGTH_SHORT).show();
//                }
//            });
            holder.text1.setText(statusticketdataConstructor.getIntid());
           // holder.text2.setText(statusticketdataConstructor.getId());

            holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.text1.setTextColor(Color.parseColor("#A6000000"));
                holder.text2.setTextColor(Color.parseColor("#A6000000"));
                Datacontainer.sharedprefrence(context.getApplicationContext());
                Intent intent = new Intent(v.getContext(), Ticket_status.class);
                intent.putExtra("Account_history_BTNO",statusticketdataConstructor.getIntid());
                CreateTicketPostdata.patchstatusofnotification(statusticketdataConstructor.getId(), new APICallBack() {
                    @Override
                    public void onResponse(boolean b) {
                        if(b){

                        }
                    }
                });

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        int limit = 10;
        return Math.min(listnotifydata.size(), limit);
     //   return listnotifydata.size();
    }



    public class notificationdataholder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView text,text1,text2;
        CardView cardView;
        public notificationdataholder(@NonNull @NotNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.notify_title);
            text1 = itemView.findViewById(R.id.notify_text);
            text2 = itemView.findViewById(R.id.notify_position);

            cardView = itemView.findViewById(R.id.notify_cardview);

        }

        @Override
        public void onClick(View v) {

        }
    }

}
