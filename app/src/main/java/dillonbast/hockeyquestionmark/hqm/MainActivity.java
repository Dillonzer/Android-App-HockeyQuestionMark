package dillonbast.hockeyquestionmark.hqm;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //if the nav drawer is closed then it back tracks through the fragments until no more
            int count = getFragmentManager().getBackStackEntryCount();
            if(count > 1){
                getFragmentManager().popBackStack();
                count--;
            }else
                super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        // TODO: Remove with update of new season
        int id = item.getItemId();

        Fragment newFragment;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (id == R.id.lhl_s) {
            // Handle the LHL Schedule
            newFragment = new LHLScheduleFragment();
            transaction.add(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.lhl_r) {
            // Handle the LHL Roster
            newFragment = new LHLRosterFragment();
            transaction.add(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.lhl_st) {
            // Handle the LHL Standings
            newFragment = new LHLStandings();
            transaction.add(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.rsl_s) {
            // Handle the RSL Schedule
            newFragment = new RSLScheduleFragment();
            transaction.add(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.rsl_r) {
            // Handle the RSL Roster
            newFragment = new RSLRosterFragment();
            transaction.add(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.rsl_st) {
            // Handle the LHL Standings
            newFragment = new RSLStandings();
            transaction.add(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.jsl_s) {
            // Handle the JSL Schedule
            newFragment = new JSLScheduleFragment();
            transaction.add(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.jsl_r) {
            // Handle the JSL Roster
            newFragment = new JSLRosterFragment();
            transaction.add(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.jsl_st) {
            // Handle the LHL Standings
            newFragment = new JSLStandings();
            transaction.add(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
