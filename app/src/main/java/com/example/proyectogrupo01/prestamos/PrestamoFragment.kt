package com.example.proyectogrupo01.prestamos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectogrupo01.ApiService
import com.example.proyectogrupo01.R
import com.example.proyectogrupo01.databinding.FragmentPrestamoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PrestamoFragment : Fragment() {

    private var _binding: FragmentPrestamoBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PrestamoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrestamoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        configurarNavegacion()
        cargarPrestamos()
    }

    private fun setupRecyclerView() {
        adapter = PrestamoAdapter { libro ->
            Toast.makeText(
                requireContext(),
                "Préstamo de '${libro.titulo}' renovado exitosamente",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.rvPrestamos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPrestamos.adapter = adapter
    }

    private fun cargarPrestamos() {
        val api: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl("https://library-64p84.ondigitalocean.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getLibros()
                }
                val prestamos = response.results.take(3)
                adapter.setData(prestamos)
            } catch (e: Exception) {
                Log.e("PRESTAMO", "Error cargando préstamos: ${e.message}")
            }
        }
    }

    private fun configurarNavegacion() {
        binding.inicio.setOnClickListener {
            findNavController().navigate(R.id.action_prestamoFragment_to_mainFragment)
        }

        binding.catalogo.setOnClickListener {
            findNavController().navigate(R.id.action_prestamoFragment_to_catalogoFragment)
        }

        binding.user.setOnClickListener {
            findNavController().navigate(R.id.action_prestamoFragment_to_perfilFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
