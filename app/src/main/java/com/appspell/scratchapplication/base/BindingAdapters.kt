package com.appspell.scratchapplication.base

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("show")
fun View.show(show: Boolean?) {
    this.visibility = if (show == true) View.VISIBLE else View.GONE
}
