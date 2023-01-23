package com.example.mahira2;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;




public class dashbord extends AppCompatActivity {

    Button addwork,finalwork,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord2);

        addwork=findViewById(R.id.addwork);
        finalwork=findViewById(R.id.finalwork);
        logout=findViewById(R.id.logout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 SharedPreferences sp=getSharedPreferences("credencial",MODE_PRIVATE);
                sp.edit().remove("mob").commit();
                sp.edit().remove("password").commit();
                sp.edit().remove("fname").commit();
                sp.edit().remove("lname").commit();
                sp.edit().remove("role").commit();
                sp.edit().apply();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                finish();
            }
        });
        addwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),addwork.class));
            }
        });

    }
}