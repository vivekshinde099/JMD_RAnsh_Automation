package com.jio.ranshjmd.Bottomnavigationbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jio.ranshjmd.Adapters.GFDResolvedTicket;
import com.jio.ranshjmd.Adapters.Getdatafromfirebase;
import com.jio.ranshjmd.ApiConstructor.AutoresListConstructor;
import com.jio.ranshjmd.ApiConstructor.TicketListConstructor;
import com.jio.ranshjmd.Models.Mygetticketdata;
import com.jio.ranshjmd.Models.Resolvedticketgetter;
import com.jio.ranshjmd.PlaceHolderAPI.AutoResTicket_PlaceHolderAPI;
import com.jio.ranshjmd.PlaceHolderAPI.TicketList_PlaceHolderAPI;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.RequestAPI.APIConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class TicketFragment extends Fragment {


    private TicketList_PlaceHolderAPI ticketList_placeHolderAPI;
    RecyclerView recyclerView,recyclerView1;
    Retrofit retrofit;
    List<Mygetticketdata> mygetticketdata;
    List<Resolvedticketgetter> resolvedticketgetters;
    List<TicketListConstructor> ticketListConstructors;
    List<AutoresListConstructor> autoresListConstructors;
    AutoResTicket_PlaceHolderAPI autoResTicket_placeHolderAPI;
    GFDResolvedTicket gfdResolvedTicket;
    Getdatafromfirebase getdatafromfirebase;
    String phonenumber;
    TextView t1,t2;

    public TicketFragment() {
        // Required empty public constructor
    }
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tickets, null);

        t1=v.findViewById(R.id.text1);
        t2=v.findViewById(R.id.text2);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView1.setVisibility(View.GONE);
                getticketid();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                recyclerView1.setVisibility(View.VISIBLE);
                getautoresticketdata();
            }
        });

        SharedPreferences getshared = getActivity().getSharedPreferences("Data", MODE_PRIVATE);
        phonenumber = getshared.getString("strmob", "Mobile Number");


        recyclerView=v.findViewById(R.id.myaccounthistoryrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(TicketFragment.this.getActivity()));
        mygetticketdata=new ArrayList<Mygetticketdata>();

        recyclerView1=v.findViewById(R.id.myaccounthistoryrecyclerview1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(TicketFragment.this.getActivity()));
        resolvedticketgetters=new ArrayList<>();

        ticketListConstructors=new ArrayList<TicketListConstructor>();
        autoresListConstructors=new ArrayList<AutoresListConstructor>();

        return v;
    }

    private void getautoresticketdata() {
        autoresListConstructors.clear();

        autoResTicket_placeHolderAPI = APIConnection.getretrofitinstance().create(AutoResTicket_PlaceHolderAPI.class);

        Call<List<AutoresListConstructor>> call = autoResTicket_placeHolderAPI.getautoresticketid(phonenumber);

        call.enqueue(new Callback<List<AutoresListConstructor>>() {
            @Override
            public void onResponse(Call<List<AutoresListConstructor>> call, Response<List<AutoresListConstructor>> response) {
                if (!response.isSuccessful()) {
                    //   textViewResult.setText("code :" + response.code());
                    return;
                }
                List<AutoresListConstructor> posts = response.body();
                for (AutoresListConstructor post : posts) {

                    String content = post.getIntid();
                    autoresListConstructors.add(new AutoresListConstructor(content));

                    Log.d("Data:", String.valueOf(autoresListConstructors.toString()));
                }

                Collections.reverse(autoresListConstructors);
                gfdResolvedTicket = new GFDResolvedTicket(autoresListConstructors,TicketFragment.this);
                recyclerView1.setAdapter(gfdResolvedTicket);
            }
            @Override
            public void onFailure(Call<List<AutoresListConstructor>> call, Throwable t) {

                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getticketid(){
        ticketListConstructors.clear();
        ticketList_placeHolderAPI = APIConnection.getretrofitinstance().create(TicketList_PlaceHolderAPI.class);
        Call<List<TicketListConstructor>> call = ticketList_placeHolderAPI.getticketid(phonenumber);

        call.enqueue(new Callback<List<TicketListConstructor>>() {
            @Override
            public void onResponse(Call<List<TicketListConstructor>> call, Response<List<TicketListConstructor>> response) {
                if (!response.isSuccessful()) {
                    //   textViewResult.setText("code :" + response.code());
                    return;
                }
                List<TicketListConstructor> posts = response.body();
                for (TicketListConstructor post : posts) {
                    String content = post.getIntid();
                    ticketListConstructors.add(new TicketListConstructor(content));

                    Log.d("Data:", String.valueOf(mygetticketdata.toString()));
                }

                Collections.reverse(ticketListConstructors);
                getdatafromfirebase = new Getdatafromfirebase(ticketListConstructors,TicketFragment.this);
                recyclerView.setAdapter(getdatafromfirebase);
            }

            @Override
            public void onFailure(Call<List<TicketListConstructor>> call, Throwable t) {

            }
        });

    }




}
