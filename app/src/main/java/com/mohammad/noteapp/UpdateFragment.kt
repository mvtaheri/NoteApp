package com.mohammad.noteapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.mohammad.noteapp.databinding.FragmentUpdateBinding
import com.mohammad.noteapp.model.Note
import com.mohammad.noteapp.viewmodel.NoteViewModel

class UpdateFragment : Fragment(R.layout.fragment_update) {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel

    // Since the Update Note Fragment contains arguments in nav_graph
    private val args: UpdateFragmentArgs by navArgs()
    private lateinit var currentNote: Note
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentNote = args.note!!
        noteViewModel = (activity as MainActivity).noteViewModel

        binding.etNoteTitleUpdate.setText(currentNote.noteTitle)
        binding.etNoteBodyUpdate.setText(currentNote.noteBody)

        binding.fabDone.setOnClickListener() {
            val title = binding.etNoteTitleUpdate.text.toString().trim()
            val body = binding.etNoteBodyUpdate.text.toString().trim()

            if (title.isNotEmpty()) {
                val note = Note(currentNote.id, title, body)
                noteViewModel.updateNote(note)
                view.findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            } else {
                Toast.makeText(
                    context,
                    "Please enter note Title",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {

            setTitle("Delete Note")
            setMessage("You want to delete this Note?")
            setPositiveButton("Delete") { _, _ ->
                noteViewModel.deleteNote(currentNote)

                view?.findNavController()?.navigate(
                    R.id.action_updateFragment_to_homeFragment
                )

            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}