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

import com.jio.ranshjmd.Bottomnavigationbar.MainCategoryIssuePage;
import com.jio.ranshjmd.Subcategory_back;
import com.jio.ranshjmd.Models.Mylistdata;
import com.jio.ranshjmd.R;


public class Maincategoryissue extends RecyclerView.Adapter<Maincategoryissue.horizontalviewholder> {

    Mylistdata[] mylistdata;
     MainCategoryIssuePage context;

    public Maincategoryissue(Mylistdata[] mylistdata, MainCategoryIssuePage category_front) {
        this.mylistdata = mylistdata;
        this.context = category_front;
    }

    @NonNull
    @NotNull
    @Override
    public horizontalviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerlist_item,parent,false);
        horizontalviewholder hv = new horizontalviewholder(view);
        return hv;

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull horizontalviewholder holder, int position) {

     //   Random rnd = new Random();
     //   int currentColor = Color.argb(255,rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
     //   holder.parent.setCardBackgroundColor(currentColor);

        final Mylistdata mylistdatalist = mylistdata[position];

        holder.text.setText(mylistdatalist.getIssuename());

//        Datacontainer.model(null,holder.text,mylistdatalist.getActivity(),"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Subcategory_back.class);
                intent.putExtra("Issue_name",mylistdatalist.getIssuename());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mylistdata.length;
    }

    public class horizontalviewholder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        CardView parent;
        TextView text;
        public horizontalviewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.texttag);
//            parent = itemView.findViewById(R.id.cardyaout);


        }

        @Override
        public void onClick(View v) {


        }
    }


}
