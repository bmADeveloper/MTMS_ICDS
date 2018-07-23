package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment.ChangePasswordFragment;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment.HomeFragment;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.AllMenuFragment.UpdateProfilesFragment;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.Utils.UserParcelable;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.WorkingPurposeFragment.AboutUsFragment;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.WorkingPurposeFragment.ContactsFragment;
import com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.WorkingPurposeFragment.GuidlineFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String ident;
    private UserParcelable user;
    private Toast toast;
    private long lastBackPressTime = 0;
    String header_email,supervisor_name_passing;
    String email_passing_updateFrg,name_passing_updateFrg ,sup_code_pass_report_generate,sup_code_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//..................................................................................................

        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
        ImageView photo = (ImageView)header.findViewById(R.id.image_menu);


        try{
            Bundle bundle =getIntent().getExtras();
            user = bundle.getParcelable("DATA_USER");
            if(bundle!=null){
                ident = user.getId();
                ((TextView) header.findViewById(R.id.tv_name_supervisor_nav_header)).setText(user.getName());
                ((TextView) header.findViewById(R.id.tv_email_supervisor_nav_header)).setText(user.getEmail());


                if(!user.getImage().equals("sin imagen")){
                    String url_image = "http://192.168.1.102/nic_icds_app/"+user.getImage();
                    //String url_image = "http://icdsjalpaiguri.in/nic_icds_app/"+user.getImage();
                    url_image = url_image.replace(" ","%20");
                    try {
                        Log.i("RESPUESTA IMAGE: ",""+url_image);
                        Glide.with(this).load(url_image).into(photo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


//..................................................................................................
// ..................................................................................................
        header_email=user.getEmail();
        email_passing_updateFrg=user.getEmail();
        name_passing_updateFrg=user.getNumber();
        sup_code_pass_report_generate=user.getId();
        supervisor_name_passing=user.getName();
       // Toast.makeText(getApplicationContext(),supervisor_name_passing,Toast.LENGTH_SHORT).show();
//.................send sup_code into HomeFrag.................................................

//..................................................................................................

       

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        HomeFragment frag = new HomeFragment();
        frag.recv_sup_code_from_main=sup_code_pass_report_generate;
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null) //add itself (FirstFragment) into the stack
                .replace(R.id.mainLayout, frag)
                .commit();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

       if (id == R.id.nav_home) {
           HomeFragment frag = new HomeFragment();
           frag.recv_sup_code_from_main=sup_code_pass_report_generate;
           getSupportFragmentManager().beginTransaction()
                   .addToBackStack(null) //add itself (FirstFragment) into the stack
                   .replace(R.id.mainLayout, frag)
                   .commit();
        }
       else if (id == R.id.nav_settings) {
       /*
            SettingsFragment settingsFragment=new SettingsFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,settingsFragment).commit();
                         OR  */
            Fragment frag = new GuidlineFragment();
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null) //add itself (FirstFragment) into the stack
                    .replace(R.id.mainLayout, frag)
                    .commit();



        } else if (id == R.id.nav_password_change) {
            ChangePasswordFragment frag = new ChangePasswordFragment();
                frag.Email_passing=header_email;
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null) //add itself (FirstFragment) into the stack
                    .replace(R.id.mainLayout, frag)
                    .commit();

        } else if (id == R.id.nav_profile_update) {
           UpdateProfilesFragment frag = new UpdateProfilesFragment();
           frag.recv_email=email_passing_updateFrg;
           frag.recv_name=name_passing_updateFrg;
           getSupportFragmentManager().beginTransaction()
                   .addToBackStack(null) //add itself (FirstFragment) into the stack
                   .replace(R.id.mainLayout, frag)
                   .commit();

        } else if (id == R.id.nav_log_out) {

           Intent moveToMain = new Intent(getBaseContext(), LoginActivity.class);
           moveToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
           moveToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
           moveToMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           startActivity(moveToMain);
           MainActivity.this.finish();
           Toast.makeText(getApplicationContext(),"Signed Out ..",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_about_us) {

           Fragment frag = new AboutUsFragment();
           getSupportFragmentManager().beginTransaction()
                   .addToBackStack(null) //add itself (FirstFragment) into the stack
                   .replace(R.id.mainLayout, frag)
                   .commit();

        } else if (id == R.id.nav_contacts) {

           Fragment frag = new ContactsFragment();
           getSupportFragmentManager().beginTransaction()
                   .addToBackStack(null) //add itself (FirstFragment) into the stack
                   .replace(R.id.mainLayout, frag)
                   .commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
