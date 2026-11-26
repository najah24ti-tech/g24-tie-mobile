
package com.example.najah_tie_mobile.note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.najah_tie_mobile.data.AppDatabase
import com.example.najah_tie_mobile.data.entity.NoteEntity
import com.example.najah_tie_mobile.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Note Form"
            setDisplayHomeAsUpEnabled(true)
        }

        db = AppDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener {

            val title = binding.etTitle.text
                .toString()
                .trim()

            val content = binding.etContent.text
                .toString()
                .trim()

            if (title.isBlank() || content.isBlank()) {

                Toast.makeText(
                    this,
                    "Isi semua kolom!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            binding.btnSaveNote.isEnabled = false

            lifecycleScope.launch {

                try {
                    val note = NoteEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )

                    db.noteDao().insert(note)

                    Toast.makeText(
                        this@NoteFormActivity,
                        "Catatan berhasil disimpan",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } catch (e: Exception) {

                    Toast.makeText(
                        this@NoteFormActivity,
                        "Gagal menyimpan: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()

                    binding.btnSaveNote.isEnabled = true
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
