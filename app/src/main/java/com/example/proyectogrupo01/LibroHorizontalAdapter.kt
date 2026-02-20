package com.example.proyectogrupo01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class LibroHorizontalAdapter(
    private val listaLibros: List<Libro>,
    private val onClick: (Libro) -> Unit
) : RecyclerView.Adapter<LibroHorizontalAdapter.LibroViewHolder>() {

    inner class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgLibro: ImageView = itemView.findViewById(R.id.imgLibro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_libro_horizontal, parent, false)
        return LibroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = listaLibros[position]
        holder.imgLibro.setImageResource(libro.imagenResId)

        holder.itemView.setOnClickListener {
            onClick(libro)
        }
    }

    override fun getItemCount(): Int = listaLibros.size
}