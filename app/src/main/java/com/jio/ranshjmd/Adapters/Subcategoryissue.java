package com.jio.ranshjmd.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import com.jio.ranshjmd.Checking_issue;
import com.jio.ranshjmd.RechargeReversal;
import com.jio.ranshjmd.SR_Form;
import com.jio.ranshjmd.Subcategory_back;
import com.jio.ranshjmd.Models.Mysubcategorymodal;
import com.jio.ranshjmd.R;

public class Subcategoryissue extends RecyclerView.Adapter<Subcategoryissue.verticalviewholder> {

    Mysubcategorymodal[] mysubcategorymodals;
    Context context;

    public Subcategoryissue (Mysubcategorymodal[] mysubcategorymodals, Subcategory_back listView_issue){
        this.mysubcategorymodals = mysubcategorymodals;
        this.context = listView_issue;
    }

    @NonNull
    @NotNull
    @Override
    public verticalviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.subissuelayout,parent,false);
        verticalviewholder vertviewholder = new verticalviewholder(view);
        return vertviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull verticalviewholder holder, int position) {


        final Mysubcategorymodal mysubcategorymodallist = mysubcategorymodals[position];

//        if (Datacontainer.getlanguage == "Hindi") {
//            Datacontainer.setFontFace(mysubcategorymodallist.getActivity(),holder.text);
            holder.text.setText(mysubcategorymodallist.getSubcategoryissuename());
//        }

     //   Datacontainer.model(null,holder.text,mysubcategorymodallist.getActivity(),"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mysubcategorymodallist.getSubcategoryissuename() == "Others"){
                    Intent intent = new Intent(v.getContext(), SR_Form.class);
                    context.startActivity(intent);
                }else if(mysubcategorymodallist.getSubcategoryissuename() == "Recharge Reversal"){
                    Intent intent = new Intent(v.getContext(), RechargeReversal.class);
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(v.getContext(), Checking_issue.class);
                    intent.putExtra("A",mysubcategorymodallist.getSubcategoryissuename());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mysubcategorymodals.length;
    }

    public class verticalviewholder extends RecyclerView.ViewHolder{

        CardView parent;
        TextView text;
        public verticalviewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.subissuetext);

        }
    }

}
