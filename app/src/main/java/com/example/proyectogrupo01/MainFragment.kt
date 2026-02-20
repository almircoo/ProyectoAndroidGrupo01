package com.example.proyectogrupo01

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectogrupo01.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun configurarNavegacion() {
        val navController = requireActivity()
            .supportFragmentManager
            .findFragmentById(R.id.MainNavfragment)!!
            .findNavController()

        binding.catalogo.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_catalogoFragment)
        }

        binding.prestamo.setOnClickListener {
           findNavController().navigate(R.id.action_mainFragment_to_prestamoFragment)
        }

        binding.user.setOnClickListener {
        }

        binding.rvPopulares.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        setupRecyclerViews()


    }

    private fun setupRecyclerViews() {

        val librosPopulares = listOf(
            Libro("Cien años de soledad", "Gabriel García Márquez", R.drawable.img_cien_anos, true),
            Libro("1984", "George Orwell", R.drawable.img_1984, false),
            Libro("Don Quijote", "Miguel de Cervantes", R.drawable.img_quijote, true),
            Libro("Sapiens", "Yuval Noah Harari", R.drawable.img_sapiens, true)
        )

        val adapterPopulares = LibroHorizontalAdapter(librosPopulares) { libro ->
            val action = MainFragmentDirections.actionMainFragmentToDetalleFragment(
                libro.titulo,
                libro.autor,
                libro.imagenResId,
                libro.disponible
            )
            findNavController().navigate(action)
        }

        binding.rvPopulares.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvPopulares.adapter = adapterPopulares
    }
}