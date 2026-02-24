package com.example.proyectogrupo01.prestamos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proyectogrupo01.R
import com.example.proyectogrupo01.databinding.ItemPrestamoBinding
import com.example.proyectogrupo01.models.LibroItem

class PrestamoAdapter(private val onRenovarClick: (LibroItem) -> Unit) :
    RecyclerView.Adapter<PrestamoAdapter.PrestamoViewHolder>() {

        private val libros = mutableListOf<LibroItem>()

        fun setData(data: List<LibroItem>) {
            libros.clear()
            libros.addAll(data)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrestamoViewHolder {
            val binding = ItemPrestamoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PrestamoViewHolder(binding)
        }

        override fun onBindViewHolder(holder: PrestamoViewHolder, position: Int) {
            holder.bind(libros[position])
        }

        override fun getItemCount(): Int = libros.size

        inner class PrestamoViewHolder(private val binding: ItemPrestamoBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(libro: LibroItem) {
                binding.apply {
                    txtTitulo.text = libro.titulo
                    txtAutor.text = libro.autorNombre
                    txtFechaVencimiento.text = "Vence: ${libro.anioPublicacion + 1} may ${2024}"

                    imgPortada.load(libro.portada)

                    btnRenovar.setOnClickListener {
                        onRenovarClick(libro)
                    }
                }
            }
        }
}