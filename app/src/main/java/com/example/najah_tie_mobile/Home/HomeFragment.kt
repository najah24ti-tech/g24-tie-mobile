
package com.example.najah_tie_mobile.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.najah_tie_mobile.R
import com.example.najah_tie_mobile.AuthActivity

import com.example.najah_tie_mobile.Photo.PhotoAdapter
import com.example.najah_tie_mobile.data.api.CatFactApiClient
import com.example.najah_tie_mobile.data.model.PhotoApiClient
import com.example.najah_tie_mobile.data.model.PhotoModel

import com.example.najah_tie_mobile.Home.pertemuan_11.ElevenActivity
import com.example.najah_tie_mobile.Home.pertemuan_4.FourthActivity
import com.example.najah_tie_mobile.Home.pertemuan_5.FifthActivity
import com.example.najah_tie_mobile.Home.pertemuan_7.SeventhActivity
import com.example.najah_tie_mobile.Home.petemuan_10.TenthActivity

import com.example.najah_tie_mobile.databinding.FragmentHomeBinding

import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var photoList: List<PhotoModel> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = "Home"
        }

        // Pertemuan 4
        binding.btnToFourth.setOnClickListener {
            startActivity(
                Intent(requireContext(), FourthActivity::class.java)
            )
        }

        // Pertemuan 5
        binding.btnToFifth.setOnClickListener {
            startActivity(
                Intent(requireContext(), FifthActivity::class.java)
            )
        }

        // Pertemuan 7
        binding.btnToSeventh.setOnClickListener {
            startActivity(
                Intent(requireContext(), SeventhActivity::class.java)
            )
        }

        // Pertemuan 10
        binding.btnToTenth.setOnClickListener {
            startActivity(
                Intent(requireContext(), TenthActivity::class.java)
            )
        }

        // Pertemuan 11
        binding.btnToEleven.setOnClickListener {
            startActivity(
                Intent(requireContext(), ElevenActivity::class.java)
            )
        }

        // Refresh Cat Fact
        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        // Pilihan Mode Gallery
        binding.toggleGalleryMode.addOnButtonCheckedListener {
                group, checkedId, isChecked ->

            if (isChecked) {
                when (checkedId) {
                    R.id.btnHorizontal -> {
                        setGalleryMode(true)
                    }

                    R.id.btnGrid -> {
                        setGalleryMode(false)
                    }
                }
            }
        }

        // Load Photo
        loadPhoto()

        // Logout
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    // Cat Fact API
    private fun loadCatFact() {

        viewLifecycleOwner.lifecycleScope.launch {

            try {
                binding.tvCatFact.text = "Loading..."

                val response =
                    CatFactApiClient.apiService.getCatFact()

                binding.tvCatFact.text = "\"${response.fact}\""

            } catch (e: Exception) {

                binding.tvCatFact.text =
                    "Gagal mengambil fakta kucing."

                Toast.makeText(
                    requireContext(),
                    "Terjadi kesalahan: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Picsum Photo API
    private fun loadPhoto() {

        viewLifecycleOwner.lifecycleScope.launch {

            try {
                photoList =
                    PhotoApiClient.apiService.getPhotos()

                binding.rvGallery.adapter =
                    PhotoAdapter(photoList)

                // Default Horizontal
                setGalleryMode(true)

            } catch (e: Exception) {

                Toast.makeText(
                    requireContext(),
                    "Gagal memuat foto: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Mengubah Mode Gallery
    private fun setGalleryMode(isHorizontal: Boolean) {

        binding.rvGallery.layoutManager =
            if (isHorizontal) {

                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

            } else {

                GridLayoutManager(
                    requireContext(),
                    2
                )
            }

        binding.rvGallery.isNestedScrollingEnabled = false
    }

    // Dialog Logout
    private fun showLogoutDialog() {

        AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("Apakah kamu yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->

                val sharedPreferences =
                    requireActivity().getSharedPreferences(
                        "user_pref",
                        MODE_PRIVATE
                    )

                sharedPreferences.edit().clear().apply()

                val intent = Intent(
                    requireContext(),
                    AuthActivity::class.java
                )

                startActivity(intent)
                requireActivity().finish()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
