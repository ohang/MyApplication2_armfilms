package com.ohanyan.goro.armfilms;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;

import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;

public class film_activity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    public static final String API_KEY="AIzaSyCwDm9DcF6BKukcGhu7LIpNyExNXXpUYls";
    Context context;
    String ID;
    String country;
    int year ;
    String description;
    String fname;
    String fcate;
    Cursor info;
    int img;
    private InterstitialAd mInterstitialAd;

    DataBaseHelper myDbHelper;

    TextView film_name;
    TextView film_desc;
    TextView film_year;
    TextView film_country;
    TextView film_category;
    ImageView fimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_page);



        film_name = (TextView)findViewById(R.id.text1);
         film_desc=  (TextView)findViewById(R.id.filmdesc);
         film_year=(TextView)findViewById(R.id.film_year);
         film_country=(TextView)findViewById(R.id.film_country);
         film_category=(TextView)findViewById(R.id.film_category);
         fimg=(ImageView)findViewById(R.id.imageView);

        String name = getIntent().getStringExtra("key");




        myDbHelper = new DataBaseHelper(this);

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

            SQLiteDatabase mydb1 = myDbHelper.getReadableDatabase();
             info = mydb1.rawQuery("SELECT * FROM film WHERE name=" +"'"+ name +"'"+ "    ", null);
            info.moveToNext();
            fname=info.getString(info.getColumnIndex("name"));
            country=info.getString(info.getColumnIndex("country"));
            year=info.getInt(info.getColumnIndex("year"));
            fcate=info.getString(info.getColumnIndex("category"));
            description=info.getString(info.getColumnIndex("description"));
            ID=info.getString(3);

            film_name.setText(fname);
            film_desc.setText(description);
            film_year.setText(year+"");
            film_country.setText(country);
            film_category.setText(fcate);
        Picasso.get()
                .load("https://raw.githubusercontent.com/ohang/haykakan/master/" + info.getString(info.getColumnIndex("img")) + ".jpg")
                .into(fimg, new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.get()
                                .load("https://raw.githubusercontent.com/ohang/haykakan/master/" + info.getString(info.getColumnIndex("img")) + ".jpg")
                                .networkPolicy(NetworkPolicy.OFFLINE)
                                .into(fimg);
                    }

                    @Override
                    public void onError(Exception e) {

                    }


                });
       ///  int id = getResources().getIdentifier((info.getString(info.getColumnIndex("img"))), "drawable", this.getPackageName());

      //  Picasso.with(getApplicationContext()).load("http://arm-film.ru/uploads/posts/2017-09/1505805385_super-mama-2.jpg").into(fimg);
      //  fimg.setImageResource(id);



        YouTubePlayerView youTubePlayerView =(YouTubePlayerView)findViewById(R.id.youtbue);
        youTubePlayerView.initialize(API_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChageListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);



        if(!b){

            youTubePlayer.cueVideo(ID);

        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }


    private PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };


    private PlayerStateChangeListener playerStateChageListener= new PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
