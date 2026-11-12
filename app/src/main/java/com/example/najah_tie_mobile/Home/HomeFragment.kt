package com.example.najah_tie_mobile.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.najah_tie_mobile.AuthActivity
import com.example.najah_tie_mobile.Home.pertemuan_11.ElevenActivity
import com.example.najah_tie_mobile.Home.pertemuan_4.FourthActivity
import com.example.najah_tie_mobile.Home.pertemuan_5.FifthActivity
import com.example.najah_tie_mobile.Home.pertemuan_7.SeventhActivity
import com.example.najah_tie_mobile.Home.petemuan_10.TenthActivity
import com.example.najah_tie_mobile.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = "Home"
        }

        // Tombol menuju Pertemuan 4
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java).apply {
                putExtra("name", "Politeknik Caltex Riau")
                putExtra("from", "Rumbai")
                putExtra("age", 25)
            }
            startActivity(intent)
        }

        // Tombol menuju Pertemuan 5
        binding.btnToFifth.setOnClickListener {
            val intent = Intent(requireContext(), FifthActivity::class.java)
            startActivity(intent)
        }

        // Tombol menuju Pertemuan 7 - Fragment
        binding.btnToSeventh.setOnClickListener {
            val intent = Intent(requireContext(), SeventhActivity::class.java)
            startActivity(intent)
        }

        // Tombol menuju Pertemuan 10
        binding.btnToTenth.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }

        // Tombol menuju Pertemuan 11
        binding.btnToEleven.setOnClickListener {
            val intent = Intent(requireContext(), ElevenActivity::class.java)
            startActivity(intent)
        }

        // Tombol Logout
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Ya") { dialog, _ ->
                    // Mengakses & menghapus SharedPreferences
                    val sharedPref =
                        requireActivity().getSharedPreferences("user_pref", MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    dialog.dismiss()

                    // Kembali ke AuthActivity & tutup MainActivity
                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}