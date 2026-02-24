package com.example.proyectogrupo01.catalogo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectogrupo01.ApiService
import com.example.proyectogrupo01.catalogo.LibroAdapter
import com.example.proyectogrupo01.MainActivity
import com.example.proyectogrupo01.R
import com.example.proyectogrupo01.databinding.FragmentCatalogoBinding
import com.example.proyectogrupo01.home.MainFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatalogoFragment : Fragment() {

    private var _binding: FragmentCatalogoBinding? = null
    private val binding get() = _binding!!

    private  lateinit var adapter: LibroAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatalogoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarNavegacion()
        setupRecycler()
        loadLibros()
    }

    private fun configurarNavegacion() {
        binding.inicio.setOnClickListener {
            findNavController().navigate(R.id.action_catalogoFragment_to_mainFragment)
        }

        binding.prestamo.setOnClickListener {
            findNavController().navigate(R.id.action_catalogoFragment_to_prestamoFragment)
        }

        binding.user.setOnClickListener {
            findNavController().navigate(R.id.action_catalogoFragment_to_perfilFragment)
        }
    }

    private fun setupRecycler() {
        adapter = LibroAdapter(emptyList()) { libro ->
            val action = CatalogoFragmentDirections.actionCatalogoFragmentToDetalleFragment(
                libro.id
            )
            findNavController().navigate(action)
        }

        binding.rvCatalogo.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCatalogo.adapter = adapter
    }

    private fun loadLibros() {
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

                adapter.updateList(response.results)
            } catch (e: Exception) {
                Log.e("CATALOGO", "Error al cargar libros: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}