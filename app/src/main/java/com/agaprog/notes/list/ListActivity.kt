package com.agaprog.notes.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agaprog.notes.R
import com.agaprog.notes.databinding.ActivityListBinding
import com.agaprog.notes.list.item.Item
import com.agaprog.notes.menu.MenuHandler

class ListActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityListBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val list = ListService(this).list
        val listAdapter = ListAdapter(
            list,
            { item, pos -> moveHandler(item, pos) },
            { item, pos -> editHandler(item, pos) },
        )

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.listContainer.layoutManager = layoutManager
        binding.listContainer.adapter = listAdapter

        //New Item Fragment
        /*val newItem = findViewById<Button>(R.id.newButton)
        newItem.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val fragment = ItemDetailFragment()
            fragmentTransaction.replace(R.id.detailLayout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
             @@ -41,6 +46,14 @@ class ListActivity : AppCompatActivity() {
        }
        }*/
    }


    private fun editHandler(item: Item, pos: Int) {

    }

    private fun moveHandler(item: Item, pos: Int) {

    }


    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var menuHandler = MenuHandler(this,"list")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addItemToLayout(text: String) {
        val textView = TextView(this)
        textView.text = text

    //    val listLayout = findViewById<LinearLayout>(R.id.listLayout)
    //    listLayout.addView(textView)
    }
}