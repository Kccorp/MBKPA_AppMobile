package com.lotte_app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListeCollector2 extends AppCompatActivity {

    private ListView lv;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_collector);

        lv = findViewById(R.id.lv_scooters);
        btnBack = findViewById(R.id.btn_back);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = ((Scooter)lv.getAdapter().getItem(i)).getIdScooter();
                Toast.makeText(ListeCollector2.this, id, Toast.LENGTH_SHORT).show();
            }

        });

        getDataSource();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ListeCollector2.this, CollectorActivity.class));

            }
        });

    }

    public List<Scooter> getDataSource(){
        RequestQueue queue = Volley.newRequestQueue(ListeCollector2.this);
        String url = "https://lotte-api.herokuapp.com/collector2";

        List<Scooter> liste_des_trott = new ArrayList<Scooter>();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("API-RESULTAT", response);

                try {
                    JSONObject global = new JSONObject(response);
                    JSONArray trots = global.getJSONArray("path");

                    for (int i = 0; i < trots.length(); i++) {
                        Scooter s = new Scooter();
                        s.setIdScooter(trots.getJSONObject(i).getInt("idScooter"));
                        s.setLat(trots.getJSONObject(i).getDouble("latitude"));
                        s.setLng(trots.getJSONObject(i).getDouble("longitude"));
                        liste_des_trott.add(s);
                    }

                    ScooterAdapter adap = new ScooterAdapter(liste_des_trott, ListeCollector2.this);
                    lv.setAdapter(adap);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListeCollector2.this, "Echec", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);


        return liste_des_trott;
    }

}
