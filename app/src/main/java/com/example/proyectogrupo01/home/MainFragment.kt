package com.example.proyectogrupo01.home

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
import com.example.proyectogrupo01.R
import com.example.proyectogrupo01.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterPopulares: LibroHorizontalAdapter
    private lateinit var adapterNuevo: LibroHorizontalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarNavegacion()
        setupRecyclerViews()
        loadInicio()
    }

    private fun configurarNavegacion() {
        val navController = findNavController()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id){
                R.id.mainFragment -> binding.inicio
                R.id.catalogoFragment -> binding.catalogo
                R.id.prestamoFragment -> binding.prestamo
            }
        }

        binding.catalogo.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_catalogoFragment)
        }

        binding.prestamo.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_prestamoFragment)
        }

        binding.user.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_perfilFragment)
        }

    }

    private fun setupRecyclerViews() {
        adapterPopulares = LibroHorizontalAdapter(emptyList()) { libro ->

            val action = MainFragmentDirections.actionMainFragmentToDetalleFragment(
                libro.id
            )
            findNavController().navigate(action)

        }

        binding.rvPopulares.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopulares.adapter = adapterPopulares

        adapterNuevo = LibroHorizontalAdapter(emptyList()) { libro ->
            val action = MainFragmentDirections.actionMainFragmentToDetalleFragment(
                libro.id
            )
            findNavController().navigate(action)
        }
        binding.rvNuevos.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvNuevos.adapter = adapterNuevo
    }

    private fun loadInicio(){
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
                    api.getInicio()
                }
                adapterPopulares.updateList(response.librosPopulares)
                adapterNuevo.updateList(response.nuevasAdquisiciones)
            } catch (e: Exception) {
                Log.e("HOME", "Error cargando inicio: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}