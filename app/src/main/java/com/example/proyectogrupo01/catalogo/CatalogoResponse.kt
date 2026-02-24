package com.example.proyectogrupo01.catalogo

import com.example.proyectogrupo01.models.LibroItem

data class CatalogoResponse(
    val count: Long,
    val next: String?,
    val previous: String?,
    val results: List<LibroItem>
)
