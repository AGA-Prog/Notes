package com.agaprog.notes.storage

import com.agaprog.notes.entities.Note

interface INotesRepository {
    fun createNotes(id: String, copyExample: Boolean)
    fun readNotes():List<String>
    fun readNotesById(id: String):List<Note>
    fun updateNotes(id: String, notes: List<Note>)
    fun deleteNotes(id:String)
}