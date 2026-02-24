package com.example.proyectogrupo01.perfil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proyectogrupo01.login.LoginActivity
import com.example.proyectogrupo01.R
import com.example.proyectogrupo01.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarNavegacion()
        configurarPerfil()
    }

    private fun configurarPerfil() {
        binding.btnCerrarSesion.setOnClickListener {
            Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }

        binding.btnEditarPerfil.setOnClickListener {
            Toast.makeText(requireContext(), "Editar perfil - Funcionalidad próximamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configurarNavegacion() {
        binding.inicio.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_mainFragment)
        }

        binding.catalogo.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_catalogoFragment)
        }

        binding.prestamo.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_prestamoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}