package com.aioki.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aioki.myapplication.DB.DBHandler;
import com.aioki.myapplication.R;
import com.aioki.myapplication.Site.SiteListAdapter;

public class SiteListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_list);

        mRecyclerView = findViewById(R.id.siteRecyclerView);
        mAddButton = findViewById(R.id.addSiteButton);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddSiteActivity.class);
                startActivity(intent);
            }
        });

        updateList();

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    public void updateList(){
        DBHandler db = new DBHandler(this);

        mRecyclerView.setAdapter(new SiteListAdapter(db.getAllSites(), getSupportFragmentManager()));
    }


}