package com.appspell.scratchapplication.features.presentation

import com.appspell.scratchapplication.databinding.ActivityMainBinding
import javax.inject.Inject

interface MainView {
    fun submitList(items: List<MarsProperty>?)
}

class MainViewImpl @Inject constructor(binding: ActivityMainBinding) : MainView {
    private val adapter = MainListAdapter()

    init {
        binding.list.adapter = adapter
    }

    override fun submitList(items: List<MarsProperty>?) {
        adapter.submitList(items ?: emptyList())
    }
}