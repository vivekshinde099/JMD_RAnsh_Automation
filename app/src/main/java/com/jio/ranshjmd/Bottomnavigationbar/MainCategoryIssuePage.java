package com.jio.ranshjmd.Bottomnavigationbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import com.jio.ranshjmd.Adapters.Maincategoryissue;
import com.jio.ranshjmd.Models.Mylistdata;
import com.jio.ranshjmd.Interfaces.OnBackPressed;
import com.jio.ranshjmd.R;

public class MainCategoryIssuePage extends Fragment implements OnBackPressed {
    int numberOfColumns = 1;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maincategoryissuepage,container,false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(MainCategoryIssuePage.this.getActivity(),numberOfColumns));
        Mylistdata[] myissuelist1 = new Mylistdata[]{
                new Mylistdata("JIO POS Plus - Login Related Issue", MainCategoryIssuePage.this.getActivity()),
                new Mylistdata("JIO MART Digital - Login Related Issue",MainCategoryIssuePage.this.getActivity()),
                new Mylistdata("JIO POS Plus - Process Related Issue",MainCategoryIssuePage.this.getActivity()),
                new Mylistdata("JIO MART Digital -  Process Related Issue",MainCategoryIssuePage.this.getActivity()),
                new Mylistdata("JIO POS Plus - Payment Related Issue",MainCategoryIssuePage.this.getActivity()),
                new Mylistdata("Hardware Related Issue",MainCategoryIssuePage.this.getActivity()),
                //new Mylistdata("Scale Fusion Related Issue",MainCategoryIssuePage.this.getActivity()),

        };
        Maincategoryissue MYISSUELISTADAP1 = new Maincategoryissue(myissuelist1,MainCategoryIssuePage.this);
        recyclerView.setAdapter(MYISSUELISTADAP1);
        return view;
    }

    public void onBackPressed() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }
}
