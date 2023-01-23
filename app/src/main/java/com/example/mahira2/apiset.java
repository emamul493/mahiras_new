package com.example.mahira2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;


import retrofit2.http.POST;


public interface apiset
{
    @FormUrlEncoded
    @POST("app_login.php")
    Call<login> getlogin(
            @Field("mob") String mob,
            @Field("password") String pass




    );
    @FormUrlEncoded
    @POST("app_fetchadddata.php")
    Call<List<fetchaddwork>> getfetchaddwork(

            @Field("role") String role

    );


    @FormUrlEncoded
    @POST("app_adddata.php")
    Call<scanAddData>  fetchScanAddData(

            @Field("barcode") String barcode,
            @Field("mob") String mob


    );


}









