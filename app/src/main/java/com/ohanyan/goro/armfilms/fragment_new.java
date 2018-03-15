package com.ohanyan.goro.armfilms;

/**
 * Created by Goro on 08.03.2018.
 */

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;
import java.util.ArrayList;

public class fragment_new extends Fragment {

    DataBaseHelper myDbHelper;
    MyRecyclerViewAdapter adapter;
    int i;
    public InterstitialAd mInterstitialAd;


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment

        View rootview =inflater.inflate(R.layout.fragment_m, parent, false);
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-9939890174467872/9550634079");

        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });


        myDbHelper = new DataBaseHelper(getActivity());

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        } catch (SQLException sqle) {

            throw sqle;

        }

        i = 0;


        ArrayList<String> names=new ArrayList<>();
        ArrayList<String> images=new ArrayList<>();


        SQLiteDatabase mydb1 = myDbHelper.getReadableDatabase();
        Cursor info = mydb1.rawQuery("SELECT * FROM film ", null);
        while(info.moveToNext()){

            names.add(info.getString(1));
            images.add("https://raw.githubusercontent.com/ohang/countries/master/"+info.getString(info.getColumnIndex("img"))+".jpg");

        }

        String[] stockArr = new String[names.size()];
        stockArr = names.toArray(stockArr);

       String[] image = new String[images.size()];
        image = images.toArray(image);


        // image= new int[]{R.drawable.theline,R.drawable.aramma,R.drawable.sup,R.drawable.vorogayt,
        //R.drawable.paxir,R.drawable.sv,R.drawable.ysh,R.drawable.ete};



        //adapter= new MyRecyclerViewAdapter(getActivity().getApplicationContext(),stockArr,image);

        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.rvNumbers);

        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        adapter = new MyRecyclerViewAdapter(getActivity(), stockArr,image);
        recyclerView.setAdapter(adapter);



        adapter.setClickListener(new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent myIntent = new Intent(getActivity(), film_activity.class);
                String film_name =(String) adapter.getItem(position);

                myIntent.putExtra("key", film_name);

                getActivity().startActivity(myIntent);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }

            }
        });

        //GridView grid = (GridView) rootview.findViewById(R.id.gridview);

      ///  grid.setAdapter(adapter);

      /*  grid.setOnItemClickListener( new AdapterView.OnItemClickListener(){




                                         @Override
                                         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                             Intent myIntent = new Intent(getActivity(), film_activity.class);
                                             String film_name =(String) adapter.getItem(i);

                                             myIntent.putExtra("key", film_name);

                                             getActivity().startActivity(myIntent);
                                             if (mInterstitialAd.isLoaded()) {
                                                 mInterstitialAd.show();
                                             }

                                         }
                                     }

        );*/

        return rootview;

    }


    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }


}