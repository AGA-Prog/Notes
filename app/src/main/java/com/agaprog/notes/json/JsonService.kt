package com.agaprog.notes.json

import android.content.Context
import android.util.Log
import com.agaprog.notes.list.ListService
import com.agaprog.notes.list.item.Item
import org.json.JSONArray
import java.io.File

class JsonService(val context: Context) {

    fun createJsonFile(name: String, copyExample: Boolean) {
        Log.d("Debug", "CREA UN NUEVO JSON: " + name)
        //TODO crear un nuevo fichero JSON

        var list = mutableListOf<Item>()

        if (copyExample) {
            list = getListFromExample()
        }
        val folder = File(context.filesDir,"jsons")
        if (!folder.exists()) {
            folder.mkdir()
        }
        val file = File(folder , name +".json")
        file.createNewFile()
    }

    private fun getListFromExample():MutableList<Item> {
        val file = context.assets.open("example.json")
        val jsonArray = JSONArray(context.assets.open("example.json").bufferedReader())
        it.readText()
    })
    val list = ListService(context).parseJson(jsonArray)
    return list
}
}