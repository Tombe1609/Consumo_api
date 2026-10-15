package com.example.mealsearchapp

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    // Buscar por nombre
    // GET https://www.themealdb.com/api/json/v1/1/search.php?s=arrabiata
    @GET("search.php")
    suspend fun buscarPorNombre(
        @Query("s") nombre: String
    ): MealResponse

    // Obtener una receta aleatoria
    // GET https://www.themealdb.com/api/json/v1/1/random.php
    @GET("random.php")
    suspend fun recetaAleatoria(): MealResponse


    // Obtener receta por ID
    @GET("lookup.php")
    suspend fun buscarPorId(
        @Query("i") id: String
    ): MealResponse

}
