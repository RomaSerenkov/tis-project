package com.example.tis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.tis.adapter.CategoryListAdapter;
import com.example.tis.breadcrumb.Breadcrumb;
import com.example.tis.dao.CategoryDAO;
import com.example.tis.entity.Category;
import com.example.tis.list.BreadcrumbList;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView navigationView;
    private CategoryDAO categoryDAO;
    private ArrayList<Category> categories;
    private ListView listCategory;
    private ArrayList<Breadcrumb> breadcrumbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        breadcrumbs = BreadcrumbList.getAll();
        setTitle(breadcrumbs.get(0).getTitle());
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
        listCategory = (ListView) findViewById(R.id.category_list);
        LinearLayout layout = (LinearLayout) findViewById(R.id.myDynamicLayout);

        for (int i = 0; i < breadcrumbs.size(); i++) {
            Button myButton = new Button(this);
            Context context = this;
            myButton.setText(breadcrumbs.get(i).getTitle());
            myButton.setId(i);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            if(i == 0){
                layoutParams.setMargins(10, 0, 4, 0);
            }else{
                layoutParams.setMargins(4, 0, 4, 0);
            }
            if(i  == breadcrumbs.size() - 1){
                myButton.setEnabled(false);
            }
            if (breadcrumbs.size() == 1){
                myButton.setVisibility(View.GONE);
            }
            final int id_ = myButton.getId();
            layout.addView(myButton, layoutParams);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String activityToStart = "com.example.tis." + breadcrumbs.get(id_).getClassName();
                    BreadcrumbList.deleteBreadcrumb(id_);
                    try {
                        Class<?> c = Class.forName(activityToStart);
                        Intent intent = new Intent(context, c);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slidein, R.anim.slideout);
                    } catch (ClassNotFoundException ignored) {
                    }
                }
            });

        }

        String document = breadcrumbs.get(0).getDocument();
        String model = breadcrumbs.get(0).getModel();

        categoryDAO = new CategoryDAO(this);
        categories = categoryDAO.findByModelAndDocument(model, document);

        CategoryListAdapter adapter = new CategoryListAdapter(this,
                R.layout.category_list_item,
                categories);

        listCategory.setAdapter(adapter);
        listCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BreadcrumbList.updateBreadcrumbCategory(0, categories.get(i).getId());
                BreadcrumbList.addBreadcrumb(new Breadcrumb(categories.get(i).getTitle(),
                        "SubCategoryActivity",
                        document,
                        model,
                        categories.get(i).getId()));
                Intent intent = new Intent(CategoryActivity.this, SubCategoryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
            }
        });
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
    protected void onUserLeaveHint() {
        //код для перехода к вашей главной активности
        super.onUserLeaveHint();
    }

    @Override
    public void onBackPressed() {
        int count = BreadcrumbList.getAll().size();
        if(count > 0)
            BreadcrumbList.deleteBreadcrumb(count - 2);
        if(count > 1) {
            Intent repair = new Intent(this, ModelsActivity.class);
            startActivity(repair);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            finish();
        }else{
            Intent repair = new Intent(this, MainActivity.class);
            startActivity(repair);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            finish();
        }
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
