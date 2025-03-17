package com.example.mymedicines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymedicines.databinding.ItemListBinding

class MedicineAdapter(private val items: List<Item>): RecyclerView.Adapter<MedicineAdapter.ViewHolder>() {

    //создание ViewHolder с Binding
    class ViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  binding = ItemListBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.textView.text = item.value
    }

}