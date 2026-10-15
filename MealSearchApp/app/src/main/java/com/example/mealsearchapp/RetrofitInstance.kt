package com.example.mealsearchapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // El "1" al final de la ruta es la clave de prueba pública de TheMealDB
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val api: MealApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApiService::class.java)
    }
}
