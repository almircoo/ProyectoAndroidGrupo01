package com.example.proyectogrupo01.models

import com.example.proyectogrupo01.models.Categoria

data class LibroItem(
    val id: Long,
    val titulo: String,
    val autorNombre: String,
    val anioPublicacion: Long,
    val portada: String,
    val descripcion: String,
    val calificacionPromedio: Double,
    val numeroResenas: Long,
    val categorias: List<Categoria>,
    val disponible: Boolean,
    val cantidadDisponible: Long,
    val esPopular: Boolean,
    val esNuevo: Boolean
)
