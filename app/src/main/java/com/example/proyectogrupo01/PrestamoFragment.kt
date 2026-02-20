package com.example.proyectogrupo01

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyectogrupo01.databinding.FragmentPrestamoBinding

class PrestamoFragment : Fragment() {

    private var _binding: FragmentPrestamoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrestamoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarNavegacion()
    }

    private fun configurarNavegacion() {
        binding.inicio.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.catalogo.setOnClickListener {
            findNavController().navigate(R.id.action_prestamoFragment_to_catalogoFragment)
        }
    }
}