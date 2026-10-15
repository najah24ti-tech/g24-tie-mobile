package com.example.najah_tie_mobile.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.najah_tie_mobile.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.title = "Home"

        binding.btnToFourth.setOnClickListener {
            // Pindahkan kode Intent Pertemuan 4 ke sini
        }

        binding.btnToFifth.setOnClickListener {
            // Pindahkan kode Intent Pertemuan 5 ke sini
        }

        binding.btnToSeventh.setOnClickListener {
            // Pindahkan kode Intent Pertemuan 7 ke sini
        }

        binding.btnLogout.setOnClickListener {
            // Pindahkan kode logout dari MainActivity ke sini
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
