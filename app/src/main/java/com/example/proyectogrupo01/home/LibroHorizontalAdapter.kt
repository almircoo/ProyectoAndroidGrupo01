package com.example.proyectogrupo01.home

import android.R.id.list
import android.content.ClipData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proyectogrupo01.models.LibroItem
import com.example.proyectogrupo01.R
import com.example.proyectogrupo01.databinding.ItemLibroHorizontalBinding

class LibroHorizontalAdapter(
    private var listaLibros: List<LibroItem>,
    private val onClick: (LibroItem) -> Unit
) : RecyclerView.Adapter<LibroHorizontalAdapter.LibroViewHolder>() {

    inner class LibroViewHolder(val biding: ItemLibroHorizontalBinding) :
        RecyclerView.ViewHolder(biding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val binding = ItemLibroHorizontalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LibroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = listaLibros[position]
        holder.biding.imgLibro.load(libro.portada)

        holder.biding.txtTitulo.text = libro.titulo
        holder.biding.txtAutor.text = libro.autorNombre

        if (libro.disponible) {
            holder.biding.txtDisponible.text = "Disponible"
            holder.biding.txtDisponible.setTextColor(
                holder.itemView.context.getColor(R.color.green_available)
            )
        } else {
            holder.biding.txtDisponible.text = "Prestado"
            holder.biding.txtDisponible.setTextColor(
                holder.itemView.context.getColor(android.R.color.holo_red_light)
            )
        }

        holder.itemView.setOnClickListener {
            onClick(libro)
        }
    }

    override fun getItemCount(): Int = listaLibros.size

    fun  updateList(newList: List<LibroItem>){
        listaLibros = newList
        notifyDataSetChanged()
    }
}