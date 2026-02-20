package com.example.proyectogrupo01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class LibroAdapter(  private val listaLibros: List<Libro>,
                     private val onClick: (Libro) -> Unit
) : RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    inner class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgLibro: ImageView = itemView.findViewById(R.id.imgLibro)
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtAutor: TextView = itemView.findViewById(R.id.txtAutor)
        val txtEstado: TextView = itemView.findViewById(R.id.txtEstado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_libro_vertical, parent, false)
        return LibroViewHolder(view)

    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = listaLibros[position]

        holder.imgLibro.setImageResource(libro.imagenResId)
        holder.txtTitulo.text = libro.titulo
        holder.txtAutor.text = libro.autor

        if (libro.disponible) {
            holder.txtEstado.text = "Disponible"
            holder.txtEstado.setTextColor(
                holder.itemView.context.getColor(R.color.green_available)
            )
        } else {
            holder.txtEstado.text = "Prestado"
            holder.txtEstado.setTextColor(
                holder.itemView.context.getColor(android.R.color.holo_red_light)
            )
        }
        holder.itemView.setOnClickListener {
            onClick(libro)
        }
    }

    override fun getItemCount(): Int = listaLibros.size
}