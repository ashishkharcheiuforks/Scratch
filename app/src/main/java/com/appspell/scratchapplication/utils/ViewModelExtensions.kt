package com.appspell.scratchapplication.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> AppCompatActivity.viewModel(crossinline impl: () -> T) =
    ViewModelProvider(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return impl.invoke() as T
        }
    }).get(T::class.java)