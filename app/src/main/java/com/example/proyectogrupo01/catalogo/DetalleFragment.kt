package com.example.proyectogrupo01.catalogo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.proyectogrupo01.ApiService
import com.example.proyectogrupo01.MainActivity
import com.example.proyectogrupo01.R
import com.example.proyectogrupo01.databinding.FragmentDetalleBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetalleFragment : Fragment() {
    private var _binding: FragmentDetalleBinding? = null
    private val binding get() = _binding!!

    private var libroId: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        libroId = arguments?.getLong("id") ?: 0

        if (libroId > 0) {
            loadDetalleLibro(libroId)
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnPrestar.setOnClickListener {
            ejecutarPrestamo()
        }

        // Configurar navegación del bottom nav
        binding.inicio.setOnClickListener {
            findNavController().navigate(R.id.action_detalleFragment_to_mainFragment)
        }

        binding.catalogo.setOnClickListener {
            findNavController().navigate(R.id.action_detalleFragment_to_catalogoFragment)
        }

        binding.prestamo.setOnClickListener {
            findNavController().navigate(R.id.action_detalleFragment_to_prestamoFragment)
        }

        binding.user.setOnClickListener {
            findNavController().navigate(R.id.action_detalleFragment_to_perfilFragment)
        }
    }


    private fun loadDetalleLibro(id: Long) {
        val api: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl("https://library-64p84.ondigitalocean.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val libro = withContext(Dispatchers.IO) {
                    api.getLibroDetalle(id)
                }

                binding.imgPortada.load(libro.portada)
                binding.txtTitulo.text = libro.titulo
                binding.txtAutor.text = libro.autorNombre
                binding.txtAnio.text = libro.anioPublicacion.toString()
                binding.txtTipo.text = if (libro.categorias.isNotEmpty()) libro.categorias[0].nombre else "Sin categoría"
                binding.txtDisponible.text = if (libro.disponible) "Disponible" else "No Disponible"
                binding.txtRating.text = "★★★★★${libro.calificacionPromedio} (${libro.numeroResenas} reseñas)"
                binding.txtResumen.text = libro.descripcion



            } catch (e: Exception) {
                Log.e("DETALLE", "Error cargando detalle: ${e.message}")
            }
        }
    }

    private fun ejecutarPrestamo() {
        val libroTitulo = binding.txtTitulo.text.toString()
        val mensaje = "Préstamo de '$libroTitulo' completado correctamente"
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show()
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
