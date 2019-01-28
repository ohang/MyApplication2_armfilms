package com.ohanyan.goro.armfilms;

/**
 * Created by Goro on 08.03.2018.
 */
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import  static  com.ohanyan.goro.armfilms.MainActivity.mInterstitialAd;
import java.io.IOException;
import java.util.ArrayList;

public class fragment_category extends Fragment {
    DataBaseHelper myDbHelper;
    MyRecyclerViewAdapter adapter;


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment



        String strtext=getArguments().getString("message");

        View rootview =inflater.inflate(R.layout.fragment_m, parent, false);

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

        ArrayList<String> names=new ArrayList<>();
        ArrayList<String> images=new ArrayList<>();

        if (strtext=="2016"){


            SQLiteDatabase mydb1 = myDbHelper.getReadableDatabase();
            Cursor info = mydb1.rawQuery("SELECT * FROM film WHERE year = " + strtext + "        " , null);
            while(info.moveToNext()) {

                names.add(info.getString(1));
                images.add("https://raw.githubusercontent.com/ohang/haykakan/master/" + info.getString(info.getColumnIndex("img")) + ".jpg");


            }




            } else

        if (strtext=="2017"){

            SQLiteDatabase mydb1 = myDbHelper.getReadableDatabase();
            Cursor info = mydb1.rawQuery("SELECT * FROM film WHERE year = " + strtext + "        " , null);
            while(info.moveToNext()){

                names.add(info.getString(1));
                images.add("https://raw.githubusercontent.com/ohang/haykakan/master/"+info.getString(info.getColumnIndex("img"))+".jpg");



            }



        } else
        if(strtext!="main"){
            SQLiteDatabase mydb1 = myDbHelper.getReadableDatabase();
            Cursor info = mydb1.rawQuery("SELECT * FROM film WHERE category = " +"'"+ strtext +"'"+ "        " , null);
           while(info.moveToNext()){

               names.add(info.getString(1));
              // images.add(getResources().getIdentifier((info.getString(info.getColumnIndex("img"))), "drawable", getActivity().getPackageName()));
               images.add("https://raw.githubusercontent.com/ohang/haykakan/master/"+info.getString(info.getColumnIndex("img"))+".jpg");


           }
        } else  {

            SQLiteDatabase mydb1 = myDbHelper.getReadableDatabase();
            Cursor info = mydb1.rawQuery("SELECT * FROM film      " , null);
            while(info.moveToNext()) {

                names.add(info.getString(1));
                // images.add(getResources().getIdentifier((info.getString(info.getColumnIndex("img"))), "drawable", getActivity().getPackageName()));
                images.add("https://raw.githubusercontent.com/ohang/haykakan/master/" + info.getString(info.getColumnIndex("img")) + ".jpg");


            }
        }

        String[] stockArr = new String[names.size()];
        stockArr = names.toArray(stockArr);

        String[] image = new String[images.size()];
       image = images.toArray(image);

        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.rvNumbers);


        Configuration conf = getResources().getConfiguration();
        int numberOfColumns =2;

        boolean isLarge = (conf.screenLayout & Configuration.SCREENLAYOUT_SIZE_LARGE) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE;

        boolean isLandscape = (conf.orientation == Configuration.ORIENTATION_LANDSCAPE);

        boolean isLargeLand = isLarge && isLandscape;
         if (isLandscape){

             numberOfColumns=3;
         }


        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        adapter = new MyRecyclerViewAdapter(getActivity().getApplicationContext(), stockArr,image);
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