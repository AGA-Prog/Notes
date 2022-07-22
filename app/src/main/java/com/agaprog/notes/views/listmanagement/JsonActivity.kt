package com.agaprog.notes.views.listmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agaprog.notes.R
import com.agaprog.notes.databinding.ActivityJsonBinding
import com.agaprog.notes.storage.JsonItem
import com.agaprog.notes.json.JsonService
import com.agaprog.notes.menu.MenuHandler

class JsonActivity : AppCompatActivity() {
    private var _binding: ActivityJsonBinding? = null
    private val binding get() = _binding!!

    private lateinit var jsonListAdapter: JsonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityJsonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonList = JsonService(this).readFileList()
        jsonListAdapter = JsonListAdapter(
            jsonList,
            { jsonItem, pos -> itemHandler(jsonItem, pos) },
            { jsonItem, pos -> deleteHandler(jsonItem, pos) }
        )

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.fileListContainer.layoutManager = layoutManager
        binding.fileListContainer.adapter = jsonListAdapter

        binding.createJsonBtn.setOnClickListener {
            createJsonHandler()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var menuHandler = MenuHandler(this,"config")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun itemHandler(jsonItem: JsonItem, pos: Int) {
        val jsonService = JsonService(this)
        val prev = jsonService.getSelectedIndex(jsonListAdapter.jsonlist)
        jsonService.selectFile(jsonListAdapter.jsonlist, jsonItem)
        jsonListAdapter.notifyItemChanged(prev)
        jsonListAdapter.notifyItemChanged(pos)
    }

    fun deleteHandler(jsonItem: JsonItem, pos: Int) {
        val jsonService = JsonService(this)
        jsonService.deleteFile(jsonItem)
        jsonListAdapter.jsonlist = jsonService.readFileList()
        jsonListAdapter.notifyItemRemoved(pos)
    }

    fun createJsonHandler() {
        val jsonService = JsonService(this)
        jsonService.createJsonFile(
            binding.jsonName.text.toString(),
            binding.exampleCheck.isChecked
        )
        val lastPos = jsonListAdapter.itemCount
        jsonListAdapter.jsonlist = jsonService.readFileList()
        jsonListAdapter.notifyItemInserted(lastPos)
    }
}