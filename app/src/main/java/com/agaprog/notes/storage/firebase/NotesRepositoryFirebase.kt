package com.agaprog.notes.storage.firebase

import android.content.Context
import com.agaprog.notes.storage.INotesRepository

class NotesRepositoryFirebase (context: Context){
    override fun createNotes(id: String, copyExample: Boolean)
    override fun readNotes(id: String, copyExample: Boolean)
    override fun updapeNotes(id: String,copyExample: Boolean)
    override fun deleteNotes(id: String,copyExample: Boolean)
}
