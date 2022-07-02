package com.lotte_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CollectorActivity extends AppCompatActivity {

    private Button btnBack, btnSite1, btnSite2, btnSite3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector);

        btnBack = findViewById(R.id.btnBack);
        btnSite1 = findViewById(R.id.btnSite1);
        btnSite2 = findViewById(R.id.btnSite2);
        btnSite3 = findViewById(R.id.btnSite3);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CollectorActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        btnSite1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CollectorActivity.this, ListeCollector1.class);
                startActivity(intent);

            }
        });

        btnSite2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CollectorActivity.this, ListeCollector2.class);
                startActivity(intent);

            }
        });

        btnSite3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CollectorActivity.this, ListeCollector3.class);
                startActivity(intent);

            }
        });

    }

}
