
package com.example.najah_tie_mobile.note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.najah_tie_mobile.data.AppDatabase
import com.example.najah_tie_mobile.data.entity.NoteEntity
import com.example.najah_tie_mobile.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    /** Variabel database & Adapter */
    private lateinit var adapter: NoteAdapter
    private lateinit var db: AppDatabase
    private val notes = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNoteBinding.inflate(
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
        (requireActivity() as AppCompatActivity).setSupportActionBar(
            binding.toolbar
        )

        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Note"
        }

        /** Inisialisasi AppDatabase & Adapter */
        db = AppDatabase.getInstance(requireContext())
        adapter = NoteAdapter(notes, this)

        binding.rvNotes.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvNotes.adapter = adapter

        /** Tambahkan garis pemisah antaritem */
        val dividerItemDecoration = DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )

        binding.rvNotes.addItemDecoration(dividerItemDecoration)

        fetchNotes()

        binding.fabAddNote.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    NoteFormActivity::class.java
                )
            )
        }
    }

    private fun fetchNotes() {
        lifecycleScope.launch {
            val data = db.noteDao().getAll() // pemanggilan query

            notes.clear()
            notes.addAll(data)

            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    fun deleteNote(note: NoteEntity) {
        lifecycleScope.launch {
            db.noteDao().delete(note) //Hapus Note
            fetchNotes()              //Fetch lagi data notes terbaru
        }
    }
}
