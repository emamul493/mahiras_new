package com.example.mahira2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apicontroler
{
    static final String url="https://manage.mahirascomfort.com";
    public static Apicontroler clintobject;
    private static Retrofit retrofit;
    Apicontroler()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized Apicontroler getInstance()
    {
        if (clintobject==null)
            clintobject=new Apicontroler();
        return clintobject;
    }
    apiset getapi()
    {
        return retrofit.create(apiset.class);
    }
}

