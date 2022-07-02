package com.lotte_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private EditText pseudoEt, passwordEt;
    private Button btn;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pseudoEt = findViewById(R.id.pseudo);
        passwordEt = findViewById(R.id.password);
        btn = findViewById(R.id.connexion);
        progressBar = findViewById(R.id.loading);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pseudo = MainActivity.this.pseudoEt.getText().toString();
                String password = MainActivity.this.passwordEt.getText().toString();

                if(pseudo.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }else {

                    // calling a method to post the data and passing our name and job.
                    postData(pseudo, password);



                }
            }
        });
    }

    private void postData(String email, String password) {

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
        LoginAPI loginAPI = retrofit.create(LoginAPI.class);

        // passing data from our text fields to our modal class.
        LoginModal modal = new LoginModal(email, password);

        // calling a method to create a post and passing our modal class.
        Call<LoginModal> call = loginAPI.createPost(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<LoginModal>() {
            @Override
            public void onResponse(Call<LoginModal> call, Response<LoginModal> response) {

                // below line is for hiding our progress bar.
                progressBar.setVisibility(View.GONE);



                if (response.code() == 200) {
                    // create a user object from the response body
                    User user = new User(response.body().getIdUser(), response.body().getIsCollector(), response.body().getName());


                    // on below line we are setting empty text
                    passwordEt.setText("");

                    // on below line we are getting our data from modal class and adding it to our string.
                    String responseString = "Bonjour " + user.getName() + "\nTon id user est " + user.getIdUser() + "\n" + "tu es un collecteur :" + user.getIsCollector();

                    // below line we are setting our
                    // string to our text view.
                    Toast.makeText(MainActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();

                    if (user.getIsCollector() == 1) {
                        Intent intent = new Intent(MainActivity.this, CollectorActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, UserSelectActivity.class);
                        intent.putExtra("name", user.getName());
                        intent.putExtra("idUser", user.getIdUser());
                        intent.putExtra("isCollector", user.getIsCollector());
                        startActivity(intent);
                    }


                } else {
                    Toast.makeText(MainActivity.this, "Identifiants incorrect", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginModal> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(MainActivity.this, "Une erreur est survenue", Toast.LENGTH_SHORT).show();
            }
        });
    }

}