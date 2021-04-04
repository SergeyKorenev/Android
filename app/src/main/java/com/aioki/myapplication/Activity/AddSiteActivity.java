package com.aioki.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aioki.myapplication.DB.DBHandler;
import com.aioki.myapplication.R;
import com.aioki.myapplication.Site.SiteModel;

public class AddSiteActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mURLEditText;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);

        mNameEditText = findViewById(R.id.nameEditText);
        mURLEditText = findViewById(R.id.URLEditText);
        mSaveButton = findViewById(R.id.saveButton);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler db = new DBHandler(getApplicationContext());
                SiteModel site = new SiteModel(mNameEditText.getText().toString(), mURLEditText.getText().toString());
                if (site.getName().isEmpty() || site.getUrl().isEmpty()){
                    Toast.makeText(AddSiteActivity.this,
                            "Enter a name and url",
                            Toast.LENGTH_LONG).show();
                } else {
                    db.addSite(site);
                    finish();
                }
            }
        });
    }
}