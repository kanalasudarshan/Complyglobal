package app.complyglobal.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import app.complyglobal.fragment.CheckedListFragment;
import app.complyglobal.fragment.DashboardFragment;
import app.complyglobal.fragment.DueTodayFragment;
import app.complyglobal.R;
import app.complyglobal.fragment.RegisterListFragment;
import app.complyglobal.fragment.RegistrationListFragment;
import app.complyglobal.dummy.DummyContent;
import app.complyglobal.service.NotificationService;

public class HomeActivity extends AppCompatActivity   implements NavigationView.OnNavigationItemSelectedListener,CheckedListFragment.OnListFragmentInteractionListener {




    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


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

        startService(new Intent(HomeActivity.this, NotificationService.class));

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
                finish();
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
        getMenuInflater().inflate(R.menu.home, menu);
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
                startActivity(new Intent(HomeActivity.this,EntityChangePopActivity.class));
                returnValue=true;
                break;
            case R.id.action_settings :
                returnValue=true;
                break;
            case R.id.about_us :
               // startActivity(new Intent(HomeActivity.this,AboutUs.class));
                startActivityForResult(new Intent(HomeActivity.this,AboutUs.class),0);
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
                fragment=new DashboardFragment();
                break;
            case R.id.nav_due_to_day:
                fragment=new DueTodayFragment();
                break;
            case R.id.nav_checked_list:
                fragment=new CheckedListFragment();
                break;
            case R.id.nav_registers:
                fragment=new RegisterListFragment();
                break;
            case R.id.nav_registrations:
                fragment=new RegistrationListFragment();
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


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
