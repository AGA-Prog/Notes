package com.agaprog.notes.storage

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.agaprog.notes.config.ConfigService
import com.agaprog.notes.list.ToDoList
import com.agaprog.notes.list.item.Item
import com.agaprog.notes.storage.JsonItem
import java.io.File

class JsonService(val context: Context) {
    private val folderName = "jsons"
    private val fileExtension = ".json"

    fun createJsonFile(name: String, copyExample: Boolean) {
        //TODO validate name, validate if file exists ...
        val folder = File(context.filesDir, folderName)
        folder.mkdir()
        Log.d("Debug", "PATH: "+folder.path+" FILESDIR: "+context.filesDir )

        val file = File(folder, name + fileExtension)
        file.createNewFile()

        var json = "[]"
        if (copyExample) {
            val gson = GsonBuilder().setPrettyPrinting().create()
            val toDoList = getListFromExample()
            json = gson.toJson(toDoList.list)
        }
        file.writeText(json)
    }

    var json: List
        if(copyExample) {
            list = getListFromExample().list
        }

        val gson = GsonBuilder().setPrettyPrinting().create()
        val json = gson.toJson(list)
        file.writeText(json)
    }

    fun readFileList(): List<JsonItem> {
        val files = mutableListOf<JsonItem>()
        val configService = ConfigService(context)
        val defaultFileName = configService.getDefaultFileName()
        val folder = File(context.filesDir, folderName)
        folder.walk().forEach {
            if (!it.isDirectory) {
                val item = JsonItem(
                    it.nameWithoutExtension,
                it.nameWithoutExtension == defaultFileName
                )
                files.add(item)
            }
        }
        return files
    }

    fun updateFile(jsonFile: JsonFile) {

    }

    fun deleteFile(json: JsonItem) {
        val folder = File(context.filesDir, folderName)
        val file = File(folder,json.name + fileExtension)
        file.delete()
        if (json.selected) {
            val configService = ConfigService(context)
            configService.setDefaultFileName("")
        }
    }

fun selectFile(list: List<JsonItem>, item: JsonItem) {
    val configService = ConfigService(context)
    configService.setDefaultFileName(item.name)
    list.forEach {
        it.selected = it.name == item.name
    }
}

    fun getSelectedIndex(list: List<JsonItem>): Int {
        return list.indexOfFirst { jsonItem -> jsonItem.selected }
    }

    fun getSelectedFile(): File {
        val configService = ConfigService(context)
        val fileName = configService.getDefaultFileName() + fileExtension
        val folder = File(context.filesDir, folderName)
        return File(folder, fileName)
    }

    private fun getListFromExample(): MutableList<Item> {
        val file = context.assets.open("values/example.json")
        val json = file.bufferedReader().use {
            it.readText()
        }
        val gson = GsonBuilder().create()
        val list = gson.fromJson(json,ToDoList::class.java)
        return list
    }
}