package com.example.proyectogrupo01

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectogrupo01.databinding.FragmentCatalogoBinding

class CatalogoFragment : Fragment() {

    private var _binding: FragmentCatalogoBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun configurarNavegacion() {
        binding.inicio.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.prestamo.setOnClickListener {
            findNavController().navigate(R.id.action_catalogoFragment_to_prestamoFragment)
        }
    }

    private fun setupRecycler() {

        val lista = listOf(
            Libro("Cien años de soledad", "Gabriel García Márquez", R.drawable.img_cien_anos, true),
            Libro("1984", "George Orwell", R.drawable.img_1984, false),
            Libro("Don Quijote", "Miguel de Cervantes", R.drawable.img_quijote, true)
        )

        val adapter = LibroAdapter(lista) { libro ->
            findNavController().navigate(R.id.action_catalogoFragment_to_detalleFragment)
        }

        binding.rvCatalogo.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCatalogo.adapter = adapter
    }
}