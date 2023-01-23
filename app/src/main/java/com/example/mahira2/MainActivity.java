package com.example.mahira2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    EditText  mob ,pass;

    Button   logon ;
    private static final String url="https://manage.mahirascomfort.com/app_login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mob =findViewById(R.id.username);
        pass =findViewById(R.id.password);
        logon =findViewById(R.id.login);
        checkuserexistance();
        logon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                insertdeta(mob.getText().toString(),pass.getText().toString());
            }
        });
    }

    private void insertdeta(String mob,String pass)
    {
        Call<login> call=Apicontroler.getInstance()
                .getapi()
                .getlogin(mob,pass);
        call.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response)
            {
                login  obj=response.body();
                String result=obj.getMessage();
                if (result.equals("login success"))
                {
                      String  fname =obj.getFirstname();
                      String  lname =obj.getLastname();
                       String  role =obj.getRole();


                    SharedPreferences sp=getSharedPreferences("credencial",MODE_PRIVATE);
                         SharedPreferences.Editor editor=sp.edit();
                         editor.putString("mob",mob);
                         editor.putString("password",pass);
                         editor.putString("fname",fname);
                         editor.putString("lname",lname);
                         editor.putString("role",role);
                         editor.commit();
                         editor.apply();
                Toast.makeText(MainActivity.this, obj.message, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),dashbord.class));
                }
                if (result.equals("user does not exist"))
                {


                    Toast.makeText(MainActivity.this, "Wrong mob & password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<login> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();
            }
        });

    }
    void   checkuserexistance()
    {
        SharedPreferences sp = getSharedPreferences("credencial", MODE_PRIVATE);
        if (sp.contains("mob"))

            startActivity(new Intent(getApplicationContext(), dashbord.class));
        else
            Toast.makeText(MainActivity.this, "plese login", Toast.LENGTH_SHORT).show();
    }
}