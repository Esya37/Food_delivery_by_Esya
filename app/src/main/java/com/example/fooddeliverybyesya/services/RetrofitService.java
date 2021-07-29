package com.example.fooddeliverybyesya.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static Retrofit retrofit = null;
    private static final String baseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private static DeliveryService deliveryService;

    public static DeliveryService getDeliveryService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
            deliveryService = retrofit.create(DeliveryService.class);
        }
        return deliveryService;
    }


}
