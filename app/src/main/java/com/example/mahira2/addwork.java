package com.example.mahira2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class addwork extends AppCompatActivity implements View.OnClickListener
{
  //RecyclerView recview;
  TextView tv, total ;
  public static TextView scandata;
  Button  scannaddbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwork);
    //  recview=findViewById(R.id.recview);
     // recview.setLayoutManager(new LinearLayoutManager(this));
       tv=findViewById(R.id.recview);
       total=findViewById(R.id.totaladdwork);
       scandata=findViewById(R.id.scandata);
      scannaddbtn=findViewById(R.id.scanaddworkbtn);
       tv.setText("");





        fetchdata();



       scannaddbtn.setOnClickListener(this);
    }




    private void fetchdata() {

        SharedPreferences sp=getSharedPreferences("credencial",MODE_PRIVATE);
        String role = sp.getString("mob", null);

        Call<List<fetchaddwork>> call=Apicontroler.getInstance()
                .getapi()
                .getfetchaddwork(role);


      call.enqueue(new Callback<List<fetchaddwork>>() {
          @Override
          public void onResponse(Call<List<fetchaddwork>> call, Response<List<fetchaddwork>> response) {
              List<fetchaddwork> data=response.body();

            total.setText("Total:"+data.size());
              //addDatAdapter adapter=new addDatAdapter(data);
             // recview.setAdapter(adapter);
            for (int i=0;i<data.size();i++)


                tv.append("Barcode No:"+data.get(i).getBarcode()+"\n Item:"+data.get(i).getItem()+"\n\n\n");
          }

          @Override
          public void onFailure(Call<List<fetchaddwork>> call, Throwable t) {
            Toast.makeText(addwork.this, "ok", Toast.LENGTH_SHORT).show();
          }
      });
    }


    @Override
    public void onClick(View view) {
        scancode();
    }

    private void scancode() {




        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES);
        options.setPrompt("Scan a barcode");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptuarAct.class);
        barloncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions>  barloncher=registerForActivityResult(new ScanContract(),result ->
    {

        if (result.getContents()!=null)
        {

            String    barcode= result.getContents();

       scandata.setText(barcode);
            SharedPreferences sp = getSharedPreferences("credencial", MODE_PRIVATE);
            String  mob=sp.getString("mob",null);

            Call<scanAddData> call=Apicontroler.getInstance()
                    .getapi()
                    .fetchScanAddData(barcode,mob);
            call.enqueue(new Callback<scanAddData>() {
                @Override
                public void onResponse(Call<scanAddData> call, Response<scanAddData> response) {
                    scanAddData scanreport=response.body();
                    String screport=scanreport.getMessage();

                    Toast.makeText(addwork.this, screport, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<scanAddData> call, Throwable t) {
                    Toast.makeText(addwork.this, "No internet", Toast.LENGTH_SHORT).show();
                }
            });



        }
    });
}