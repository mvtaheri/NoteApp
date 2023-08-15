package com.mohammad.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohammad.noteapp.database.NoteRepository

class NoteViewModelFactory(
    val app: Application,
    private val noteRepository: NoteRepository
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app, noteRepository) as T
    }

}