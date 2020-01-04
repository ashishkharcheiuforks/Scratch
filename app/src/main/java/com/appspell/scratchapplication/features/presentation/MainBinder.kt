package com.appspell.scratchapplication.features.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.appspell.scratchapplication.base.Binder
import com.appspell.scratchapplication.databinding.ActivityMainBinding
import javax.inject.Inject

class MainBinder @Inject constructor(
    private val binding: ActivityMainBinding,
    private val viewModel: MainViewModel,
    private val view: MainView
) : Binder {

    init {
        binding.viewModel = viewModel
    }

    override fun bind(owner: LifecycleOwner) {
        binding.lifecycleOwner = owner

        viewModel.items.observe(owner, Observer { view.submitList(it) })
    }

}