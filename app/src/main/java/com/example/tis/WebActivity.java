package com.example.tis;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.tis.adapter.DocListAdapter;
import com.example.tis.adapter.ModelListAdapter;
import com.example.tis.breadcrumb.Breadcrumb;
import com.example.tis.dao.DocDao;
import com.example.tis.entity.Doc;
import com.example.tis.file.HtmlFile;
import com.example.tis.list.BreadcrumbList;

import android.os.Environment;
import android.os.storage.OnObbStateChangeListener;
import android.os.storage.StorageManager;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class WebActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private StorageManager storageManager;
    private OnObbStateChangeListener mount_listener;
    private String doc;
    private WebView web;
    private ProgressBar progressBar;
    private final String OBB_PATH = Environment.getExternalStorageDirectory() + "/Android/obb/com.example.tis";
    private final String OBB_FILE_PATH = OBB_PATH + File.separator + "main.2.com.example.tis.obb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        setTitle(BreadcrumbList.getAll().get(0).getTitle());
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
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Intent intent = getIntent();
        doc = intent.getStringExtra("docFileName");

        web = (WebView) findViewById(R.id.web_view);
        web.setWebChromeClient(new WebChromeClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setSupportZoom(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            web.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    Intent intent = new Intent(WebActivity.this, WebActivity.class);
                    intent.putExtra("docFileName", request.getUrl().toString().split("/")[3]);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slidein, R.anim.slideout);
                    return true;
                }

                public void onPageFinished(WebView view, String url) {
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    web.setVisibility(WebView.VISIBLE);
                }
            });
        } else {
            web.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Intent intent = new Intent(WebActivity.this, WebActivity.class);
                    intent.putExtra("docFileName", url.split("/")[3]);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slidein, R.anim.slideout);
                    return true;
                }

                public void onPageFinished(WebView view, String url) {
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    web.setVisibility(WebView.VISIBLE);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        progressBar.setVisibility(ProgressBar.VISIBLE);
        web.setVisibility(WebView.INVISIBLE);

        mountObb();


    }

    private void mountObb(){
        storageManager = (StorageManager) getApplicationContext().getSystemService(STORAGE_SERVICE);
        File obbFile = new File(OBB_FILE_PATH);
        mount_listener = new OnObbStateChangeListener() {
            public void onObbStateChange(String path, int state) {
                if (state == OnObbStateChangeListener.MOUNTED) {
                    if (storageManager.isObbMounted(OBB_FILE_PATH)) {
                        File mountedObbContent = new File(storageManager.getMountedObbPath(OBB_FILE_PATH));
                        String filePath = mountedObbContent.getAbsolutePath() + "/www/" + doc + ".html";
                        //String filePath = "www/" + doc + ".html";
                        try {
                            String html = HtmlFile.read(filePath, getApplicationContext());
                            html = html.replaceAll("%%s", mountedObbContent.getAbsolutePath());
                            html = html.replaceAll("doc", "https://tis.bmwcats.com/doc");
                            web.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    //Toast.makeText(getBaseContext(), "mount failed " + state, Toast.LENGTH_LONG).show();
                }
            }
        };

        storageManager.mountObb(obbFile.getAbsolutePath(), null , mount_listener);
    }


    private void unMountObb(){
        File obbFile = new File(OBB_FILE_PATH);
        storageManager.unmountObb(obbFile.getAbsolutePath(), true , mount_listener);
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
    protected void onPause() {
        super.onPause();
        unMountObb();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unMountObb();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unMountObb();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
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
