package com.example.najah_tie_mobile.Home.pertemuan_11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.najah_tie_mobile.R
import com.example.najah_tie_mobile.databinding.ActivityElevenBinding
import com.google.android.material.tabs.TabLayoutMediator

class ElevenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityElevenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi ViewBinding
        binding = ActivityElevenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar dan Tombol Back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // 1. Inisialisasi Adapter
        val tabsAdapter = ElevenTabsAdapter(this)

        // 2. Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Atur judul untuk setiap tab
            when (position) {
                0 -> {
                    tab.text = "Tab A"
                    //Tambah Icon
                    tab.icon = ContextCompat.getDrawable(  this, R.drawable.ic_home)
                    //Tambah Badge Tanpa nomor (hanya titik)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                1 -> {
                    tab.text = "Tab B"
                    //Tambah Icon
                    tab.icon = ContextCompat.getDrawable(  this, R.drawable.ic_home)
                    //Tambah Badge dengan nomor
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
            }
        }.attach()
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

}