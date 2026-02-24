package com.example.proyectogrupo01.catalogo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proyectogrupo01.R
import com.example.proyectogrupo01.databinding.ItemLibroVerticalBinding
import com.example.proyectogrupo01.models.LibroItem

class LibroAdapter(private var listaLibros: List<LibroItem>,
                   private val onClick: (LibroItem) -> Unit
) : RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    inner class LibroViewHolder(val binding: ItemLibroVerticalBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val binding = ItemLibroVerticalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LibroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = listaLibros[position]

        holder.binding.imgPortada.load(libro.portada)
        holder.binding.txtTitulo.text = libro.titulo
        holder.binding.txtAutor.text = libro.autorNombre

        if (libro.disponible) {
            holder.binding.txtEstado.text = "Disponible"
            holder.binding.txtEstado.setTextColor(
                holder.itemView.context.getColor(R.color.green_available)
            )
        } else {
            holder.binding.txtEstado.text = "Prestado"
            holder.binding.txtEstado.setTextColor(
                holder.itemView.context.getColor(android.R.color.holo_red_light)
            )
        }

        holder.itemView.setOnClickListener {
            onClick(libro)
        }
    }

    override fun getItemCount(): Int = listaLibros.size

    fun updateList(newList: List<LibroItem>) {
        listaLibros = newList
        notifyDataSetChanged()
    }
}