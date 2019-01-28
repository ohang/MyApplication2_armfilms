package com.ohanyan.goro.armfilms;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static InterstitialAd mInterstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-9939890174467872~1715541934");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9939890174467872/8899374601");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        Bundle bundle=new Bundle();
        bundle.putString("message", "main");

        fragment_category fghys= new fragment_category();
        fghys.setArguments(bundle);

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.your_placeholder, fghys);
        ft.commit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ArmFilm");
        toolbar.setLogo(R.drawable.ccc);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
       MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
       final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
       searchView.setQueryHint("Որոնում");
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String query) {
               Bundle bundle=new Bundle();
               bundle.putString("message", query);
               fragment_search mart= new fragment_search();
               mart.setArguments(bundle);
               android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
               ft.replace(R.id.your_placeholder, mart);
               ft.commit();
               return false;
           }
       });


       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        item.setChecked(true);
        if (id == R.id.page_main) {
            Bundle bundle=new Bundle();
            bundle.putString("message", "main");

            fragment_category fghys= new fragment_category();
            fghys.setArguments(bundle);

            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.your_placeholder, fghys);
            ft.commit();




        } else if (id == R.id.page_war) {

            Bundle bundle=new Bundle();
            bundle.putString("message", "Պատերազմական");
            fragment_category fghys= new fragment_category();
            fghys.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.your_placeholder, fghys);
            ft.commit();



        } else if (id == R.id.page_humor) {

            Bundle bundle=new Bundle();
            bundle.putString("message", "Կատակերգություն");
            fragment_category humor= new fragment_category();
            humor.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.your_placeholder, humor);
            ft.commit();

        } else if (id == R.id.page_2016) {
            Bundle bundle=new Bundle();
            bundle.putString("message", "2016");
            fragment_category fghys= new fragment_category();
            fghys.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.your_placeholder, fghys);
            ft.commit();


        }
        else if (id == R.id.page_2017) {



            Bundle bundle=new Bundle();
            bundle.putString("message", "2017");

            fragment_category fghys= new fragment_category();
            fghys.setArguments(bundle);

            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.your_placeholder, fghys);
            ft.commit();
        }
        else if (id == R.id.page_drama) {
            Bundle bundle=new Bundle();
            bundle.putString("message", "Դրամա");
            fragment_category drama= new fragment_category();
            drama.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.your_placeholder, drama);
            ft.commit();


        } else if (id == R.id.page_melodrama){
            Bundle bundle=new Bundle();
            bundle.putString("message", "Մելոդրամա");
            fragment_category drama= new fragment_category();
            drama.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.your_placeholder, drama);
            ft.commit();


        } else  if(id== R.id.page_mart){
            Bundle bundle=new Bundle();
            bundle.putString("message", "Մարտաֆիլմ");
            fragment_category mart= new fragment_category();
            mart.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.your_placeholder, mart);
            ft.commit();


        }
        else if (id == R.id.page_dokument) {
            Bundle bundle = new Bundle();
            bundle.putString("message", "Վավերագրական");
            fragment_category doc= new fragment_category();
            doc.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.your_placeholder, doc);
            ft.commit();
        }
        else  if(id== R.id.page_multfilm){
            Bundle bundle=new Bundle();
            bundle.putString("message", "Մուլտֆիլմ");
            fragment_category mult= new fragment_category();
            mult.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.your_placeholder, mult);
            ft.commit();


        }

       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
