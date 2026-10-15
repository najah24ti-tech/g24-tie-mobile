package com.example.najah_tie_mobile.Home.pertemuan_7

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.najah_tie_mobile.R
import com.example.najah_tie_mobile.databinding.ActivitySeventhBinding

class SeventhActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeventhBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySeventhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur Toolbar
        setSupportActionBar(binding.toolbarSeventh)

        supportActionBar?.apply {
            title = "Pertemuan 7"
            setDisplayHomeAsUpEnabled(true)
        }

        // Menampilkan Fragment pertama
        if (savedInstanceState == null) {
            replaceFragment(SatuFragment())
        }

        // Tombol Fragment 1
        binding.btnFragment1.setOnClickListener {
            replaceFragment(SatuFragment())
        }

        // Tombol Fragment 2
        binding.btnFragment2.setOnClickListener {
            replaceFragment(DuaFragment())
        }

        // Tombol Fragment 3
        binding.btnFragment3.setOnClickListener {
            replaceFragment(TigaFragment())
        }
    }

    // Tombol kembali pada Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    // Fungsi untuk mengganti Fragment
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
