package com.example.anhmarvelapi.lecturaapi

import com.example.anhmarvelapi.objetos.Comic
import com.example.anhmarvelapi.objetos.Personaje
import com.example.anhmarvelapi.objetos.Serie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {

    //Llamada de prueba que obtiene datos de un personaje específico
    @GET("v1/public/characters/1011334")
    @Deprecated("Se usó inicialmente para pruebas. No se está usando ahora")
    fun obtenerPersonaje(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Call<ApiResponse<Personaje>>

    @GET("v1/public/characters")
    fun obtenerTodosPersonajes(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: String = 100.toString()
    ): Call<ApiResponse<Personaje>>

    @GET("v1/public/comics")
    fun obtenerTodosComics(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: String = 100.toString()
    ): Call<ApiResponse<Comic>>

    @GET("v1/public/series")
    fun obtenerTodosSeries(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: String = 100.toString()
    ): Call<ApiResponse<Serie>>
}

