package com.appspell.scratchapplication.base

import androidx.lifecycle.LifecycleOwner

interface Binder {
    fun bind(owner: LifecycleOwner)
}