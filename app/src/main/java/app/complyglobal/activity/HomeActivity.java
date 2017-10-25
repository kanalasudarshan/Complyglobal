package app.complyglobal.activity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import app.complyglobal.R;
import app.complyglobal.adapter.TabsPagerAdapter;
import app.complyglobal.dummy.DummyContent;
import app.complyglobal.fragment.CheckedListFragment;
import app.complyglobal.fragment.DashboardFragment;
import app.complyglobal.fragment.DueTodayFragment;
import app.complyglobal.fragment.RegisterListFragment;
import app.complyglobal.fragment.RegistrationListFragment;
import app.complyglobal.helper.BottomNavigationViewHelper;
import app.complyglobal.helper.ServiceHelper;
import app.complyglobal.service.NotificationService;
import app.complyglobal.utils.SchedulerUtil;

public class HomeActivity extends AppCompatActivity   implements NavigationView.OnNavigationItemSelectedListener{




    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabsPagerAdapter dashboardTabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Bangalore Entity");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bnav = (BottomNavigationView) findViewById(R.id.bottombar);
        BottomNavigationViewHelper.disableShiftMode(bnav);

        /*Fragment fragment=new DashboardFragment();
        if(fragment!=null){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.home_content,fragment);
            ft.commit();
        }*/

        dashboardTabs = new TabsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.tabsViewPage);
        mViewPager.setAdapter(dashboardTabs);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.dashboardTabs);
        tabLayout.setupWithViewPager(mViewPager);

        ServiceHelper restHelper=ServiceHelper.getInstance(getApplicationContext());
        restHelper.getResponce();
        SchedulerUtil.scheduleJob(getApplicationContext());

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to exit the App?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                System.exit(0);
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        boolean returnValue=false;
        switch (id){
            case R.id.change_entity :
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setView(R.layout.activity_entity_change_pop);
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Intent homepage=new Intent(HomeActivity.this,HomeActivity.class);
                        startActivity(homepage);
                    }
                });
                builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert=builder.create();
                alert.show();

                returnValue=true;
                break;
            case R.id.action_settings :
                startActivity(new Intent(HomeActivity.this,Settings.class));
                returnValue=true;
                break;
            case R.id.about_us :
                startActivity(new Intent(HomeActivity.this,AboutUs.class));
                returnValue=true;
                break;
            default:
                returnValue=super.onOptionsItemSelected(item);
                break;
        }

        return returnValue;
    }


    private void displaySelectedScreen(int id){
        Fragment fragment=null;
        switch (id) {
            case R.id.nav_dashboard:
                //fragment=new DashboardFragment();
                break;
            case R.id.nav_calendar:
                startActivity(new Intent(HomeActivity.this,CalendarActivity.class));
                break;
            case R.id.nav_due_to_day:
                startActivity(new Intent(HomeActivity.this,CalendarCompliance.class));
                break;
            case R.id.nav_checked_list:
                startActivity(new Intent(HomeActivity.this,CheckedList.class));
                break;
            case R.id.nav_registers:
                startActivity(new Intent(HomeActivity.this,Register.class));
                break;
            case R.id.nav_registrations:
                startActivity(new Intent(HomeActivity.this,Registrations.class));
                break;
            case R.id.nav_user_account :
                startActivity(new Intent(HomeActivity.this,UserAccount.class));
                break;
            case R.id.logout :
                onBackPressed();
                break;
        }
        if(fragment!=null){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.home_content,fragment);
            ft.commit();
        }

        DrawerLayout  drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }



}
