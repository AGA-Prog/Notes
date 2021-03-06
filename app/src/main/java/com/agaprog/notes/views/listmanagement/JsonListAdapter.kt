package com.agaprog.notes.views.listmanagement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agaprog.notes.databinding.ItemJsonListBinding
import com.agaprog.notes.storage.JsonItem

class JsonListAdapter (
    var jsonlist : List<JsonItem>,
    var itemHandler: ((jsonItem: JsonItem, pos: Int) -> Unit),
    var deleteHandler: ((jsonItem: JsonItem, pos: Int) -> Unit)
): RecyclerView.Adapter<JsonListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemJsonListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemJsonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(jsonlist[position]) {
                binding.root.setOnClickListener{ itemHandler(this, position) }
                binding.jsonItemName.text =this.name
                binding.jsonItemSelected.isChecked = this.selected
                binding.jsonItemRemoveBtn.setOnClickListener { deleteHandler(this, position)}
            }
        }
    }

    override fun getItemCount(): Int {
        return jsonlist.size
    }
}