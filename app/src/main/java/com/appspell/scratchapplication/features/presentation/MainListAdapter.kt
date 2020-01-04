package com.appspell.scratchapplication.features.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appspell.scratchapplication.R
import com.appspell.scratchapplication.databinding.ItemMainBinding

class MainListAdapter : ListAdapter<MarsProperty, RecyclerView.ViewHolder>(Differ()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bindings: ItemMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_main, parent, false)
        return ItemViewHold(bindings)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHold)?.bind(getItem(position))
    }

    class ItemViewHold(private val bindings: ItemMainBinding) : RecyclerView.ViewHolder(bindings.root) {

        fun bind(item: MarsProperty) {
            bindings.item = item
        }
    }

    class Differ : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem == newItem
        }

    }
}