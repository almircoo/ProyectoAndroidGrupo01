package com.example.proyectogrupo01.home

import com.example.proyectogrupo01.models.LibroItem

data class HomeResponse (
    val librosPopulares: List<LibroItem>,
    val nuevasAdquisiciones: List<LibroItem>
)