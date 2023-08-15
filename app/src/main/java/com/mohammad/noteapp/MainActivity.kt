package com.mohammad.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.mohammad.noteapp.database.NoteDataBase
import com.mohammad.noteapp.database.NoteRepository
import com.mohammad.noteapp.databinding.ActivityMainBinding
import com.mohammad.noteapp.viewmodel.NoteViewModel
import com.mohammad.noteapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var noteViewModel: NoteViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        initApp()
    }

    private fun initApp() {

        val noteRepository = NoteRepository(NoteDataBase(this))
        val factory = NoteViewModelFactory(application, noteRepository)
        noteViewModel = ViewModelProvider(
            this, factory
        ).get(NoteViewModel::class.java)
    }
}