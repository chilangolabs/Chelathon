package com.chilangolabs.modelonow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.chilangolabs.modelonow.customwidgets.TextViewMontserrat;
import com.chilangolabs.modelonow.fragments.FragmentProductList;
import com.chilangolabs.modelonow.fragments.FragmentPromos;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static public DrawerLayout drawerLayout;
    Toolbar toolbar;
    SharedPreferences app_preference;
    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        header = navigationView.inflateHeaderView(R.layout.drawer_header);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        app_preference = this.getSharedPreferences(getString(R.string.preference_name), Context.MODE_PRIVATE);
        ImageView imgProfile = (ImageView) header.findViewById(R.id.profile_image);
        TextViewMontserrat txtName = (TextViewMontserrat) header.findViewById(R.id.headerTxtName);
        TextViewMontserrat txtLastname = (TextViewMontserrat) header.findViewById(R.id.headerTxtLastName);

        Picasso.with(this).load("https:\\/\\/scontent.xx.fbcdn.net\\/hprofile-xfa1\\/v\\/t1.0-1\\/p200x200\\/11188375_10207005853609020_211454992413364429_n.jpg?_nc_eui=ARhybBpY6ATs4Jenyins6OxjoZ3YJwzGXJZeLK6ba-569n2E_8oHBg&oh=5789feb71bedcb70185e4c7dfd8aed1d&oe=575EBC9C").error(R.mipmap.user_profile).into(imgProfile);
        txtName.setText(app_preference.getString("userName", "Sebastian"));
        txtLastname.setText(app_preference.getString("userLastName", "Tellez Orozco"));

        changeFragment(new FragmentProductList());

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.opendrawer, R.string.closedrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }

        drawerLayout.closeDrawers();

        switch (item.getItemId()) {
            case R.id.nav_item_inicio:
                changeFragment(new FragmentProductList());
                toolbar.setTitle("Mi Tienda");
                return true;
            case R.id.nav_item_promos:
                changeFragment(new FragmentPromos());
                toolbar.setTitle("Promociones");
                return true;
            case R.id.nav_item_logout:
                LoginManager.getInstance().logOut();
                finish();
                startActivity(new Intent(DrawerActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_NO_HISTORY));
//                changeFragment(new FragmentProductList());
//                toolbar.setTitle("Subastas");
                return true;
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                startActivity(new Intent(DrawerActivity.this, UserInfoActivity.class));
//                changeFragment(new UserInfoFragment());
            }
        });
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

}
