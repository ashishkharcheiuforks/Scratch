package com.appspell.scratchapplication.base

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.appspell.scratchapplication.R
import com.bumptech.glide.Glide

@BindingAdapter("show")
fun View.show(show: Boolean?) {
    this.visibility = if (show == true) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    if (url.isNotEmpty()) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error)
            .into(this)
    }
}