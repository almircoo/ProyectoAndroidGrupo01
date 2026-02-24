package com.example.proyectogrupo01

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.proyectogrupo01.catalogo.CatalogoResponse
import com.example.proyectogrupo01.home.HomeResponse
import com.example.proyectogrupo01.models.LibroItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/v1/inicio/")
    suspend fun getInicio(): HomeResponse

    @GET("api/v1/libros/")
    suspend fun getLibros(): CatalogoResponse

    @GET("api/v1/libros/{id}/")
    suspend fun getLibroDetalle(@Path("id") id: Long): LibroItem

}