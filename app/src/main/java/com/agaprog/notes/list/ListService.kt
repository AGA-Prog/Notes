package com.agaprog.notes.list

import android.content.Context
import android.util.Log
import com.agaprog.notes.config.ConfigService
import com.agaprog.notes.entities.Note
import com.agaprog.notes.json.JsonService
import com.agaprog.notes.list.item.Item
import com.agaprog.notes.storage.INotesRepository
import com.agaprog.notes.storage.StorageTypes
import com.agaprog.notes.storage.firebase.NotesRepositoryFirebase
import com.agaprog.notes.storage.local.NotesRepositoryLocal
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONArray
import java.io.IOException

class ListService constructor(val context: Context) {

    lateinit var list : ToDoList
    lateinit var notes: List<Note>
    lateinit var notesRepository : INotesRepository
    private lateinit var configService: ConfigService

    init {
        val defaultId = String
        configService = ConfigService(context)
        when (configService.getSelectedStorage()) {
            StorageTypes.FIREBASE -> {
                notesRepository = NotesRepositoryFirebase(context)
                defaultId = configService.getDefaultFirebaseNode()
            }
            StorageTypes.LOCAL -> {
                notesRepository = NotesRepositoryLocal(context)
                defaultId = configService.getDefaultFileName()
            }
        }
        notes = notesRepository.readNotesById(configService)
    }

    fun getListFromFile() {
        val file = JsonService(context).getSelectedFile()
        val json = file.inputStream().bufferedReader().use { it.readText() }

        val gson = GsonBuilder().create()
        list = gson.fromJson(json, MutableList<Item>)
    }
}