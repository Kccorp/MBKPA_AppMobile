package com.lotte_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserSelectActivity extends AppCompatActivity {

    private Button btnReport, btnStop, btnBack;
    private TextView HelloUser;
    private ProgressBar progressBar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_select);

        String name = getIntent().getStringExtra("name");
        int idUser = getIntent().getIntExtra("idUser", 0);
        int isCollector = getIntent().getIntExtra("isCollector", 0);

        User user = new User(idUser, isCollector, name);

        btnReport = findViewById(R.id.btnReport);
        btnStop = findViewById(R.id.btnStop);
        btnBack = findViewById(R.id.btnBack);

        HelloUser = findViewById(R.id.twHello);

        progressBar = findViewById(R.id.loading);

        HelloUser.setText("Bonjour "+user.getName()+" !");

        this.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UserSelectActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        this.btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteData(user);

            }
        });
    }

    private void deleteData(User user) {

        // below line is for displaying our progress bar.
        progressBar.setVisibility(View.VISIBLE);

        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lotte-api.herokuapp.com/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RideAPI rideAPI = retrofit.create(RideAPI.class);

        // passing data from our text fields to our modal class.
        // convert int to string
//        String idUser = String.valueOf(id);
        RideModal modal = new RideModal(user.getIdUser());

        // calling a method to create a post and passing our modal class.
        Call<RideModal> call = rideAPI.createDelete(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<RideModal>() {
            @Override
            public void onResponse(Call<RideModal> call, Response<RideModal> response) {

                // below line is for hiding our progress bar.
                progressBar.setVisibility(View.GONE);


                if (response.code() == 200) {
                    // create a user object from the response body
//                    User user = new User(response.body().getIdUser(), response.body().getIsCollector(), response.body().getName());


                    // on below line we are setting empty text
//                    passwordEt.setText("");

                    // on below line we are getting our data from modal class and adding it to our string.
//                    String responseString = "Bonjour " + user.getName() + "\nTon id user est " + user.getIdUser() + "\n" + "tu es un collecteur :" + user.getIsCollector();

                    // below line we are setting our
                    // string to our text view.
                    Toast.makeText(UserSelectActivity.this, "Nous avons pris en compte votre signalement", Toast.LENGTH_SHORT).show();

//                    Intent intent = new Intent(UserSelectActivity.this, UserSelectActivity.class);
//                    intent.putExtra("name", user.getName());
//                    intent.putExtra("idUser", user.getIdUser());
//                    intent.putExtra("isCollector", user.getIsCollector());
//                    startActivity(intent);

                } else {
                    Toast.makeText(UserSelectActivity.this, "erreur = "+response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RideModal> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(UserSelectActivity.this, "Une erreur est survenue", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
