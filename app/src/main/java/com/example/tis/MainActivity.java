package com.example.tis;

import android.content.Intent;
import android.os.Bundle;


import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;


import com.example.tis.breadcrumb.Breadcrumb;
import com.example.tis.list.BreadcrumbList;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private ArrayList<Breadcrumb> breadcrumbs;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getIntent().getBooleanExtra("finish", false)) finish();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title_activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        breadcrumbs = new ArrayList<>();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_repair:
                BreadcrumbList.deleteBreadcrumb(-1);
                BreadcrumbList.addBreadcrumb(new Breadcrumb(getResources().getString(R.string.menu_repair), "ModelsActivity", "repair"));
                Intent repair = new Intent(this, ModelsActivity.class);
                startActivity(repair);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                break;
            case R.id.nav_home:
                Intent home = new Intent(this, MainActivity.class);
                startActivity(home);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                break;
            case R.id.nav_technical:
                BreadcrumbList.deleteBreadcrumb(-1);
                BreadcrumbList.addBreadcrumb(new Breadcrumb(getResources().getString(R.string.menu_technical), "ModelsActivity", "technical"));
                Intent te = new Intent(this, ModelsActivity.class);
                startActivity(te);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                break;
            case R.id.nav_torque:
                BreadcrumbList.deleteBreadcrumb(-1);
                BreadcrumbList.addBreadcrumb(new Breadcrumb(getResources().getString(R.string.menu_torque), "ModelsActivity", "torque"));
                Intent to = new Intent(this, ModelsActivity.class);
                startActivity(to);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                break;
            case R.id.nav_information:
                BreadcrumbList.deleteBreadcrumb(-1);
                BreadcrumbList.addBreadcrumb(new Breadcrumb(getResources().getString(R.string.menu_information), "CategoryActivity", "information", "E0"));
                Intent in = new Intent(this, CategoryActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                break;
            case R.id.nav_liquid:
                BreadcrumbList.deleteBreadcrumb(-1);
                BreadcrumbList.addBreadcrumb(new Breadcrumb(getResources().getString(R.string.menu_liquid), "CategoryActivity", "liquid", "E0"));
                Intent li = new Intent(this, CategoryActivity.class);
                startActivity(li);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                break;
            case R.id.nav_description:
                BreadcrumbList.deleteBreadcrumb(-1);
                BreadcrumbList.addBreadcrumb(new Breadcrumb(getResources().getString(R.string.menu_description), "CategoryActivity", "description", "E0"));
                Intent de = new Intent(this, CategoryActivity.class);
                startActivity(de);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                break;
            case R.id.nav_diagnostic:
                BreadcrumbList.deleteBreadcrumb(-1);
                BreadcrumbList.addBreadcrumb(new Breadcrumb(getResources().getString(R.string.menu_diagnostic), "CategoryActivity", "diagnostic", "E0"));
                Intent di = new Intent(this, CategoryActivity.class);
                startActivity(di);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                break;
            case R.id.nav_facility:
                BreadcrumbList.deleteBreadcrumb(-1);
                BreadcrumbList.addBreadcrumb(new Breadcrumb(getResources().getString(R.string.menu_facility), "CategoryActivity", "facility", "E0"));
                Intent fa = new Intent(this, CategoryActivity.class);
                startActivity(fa);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                break;
            case R.id.nav_equipment:
                BreadcrumbList.deleteBreadcrumb(-1);
                BreadcrumbList.addBreadcrumb(new Breadcrumb(getResources().getString(R.string.menu_equipment), "CategoryActivity", "equipment", "E0"));
                Intent eq = new Intent(this, CategoryActivity.class);
                startActivity(eq);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("finish", true);
            startActivity(intent);
        } else {
            Toast.makeText(getBaseContext(), R.string.finish_app, Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_close:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("finish", true);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
