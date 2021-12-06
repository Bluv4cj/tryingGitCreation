package com.edib.devbyteview.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Binding adapter used to hide the spinner once data is available.
 */

@BindingAdapter("isNetworkError", "Playlist")
fun hideIfNetworkError(view : View, isNetworkError : Boolean, playlist : Any?){
    view.visibility = if(playlist != null) View.GONE else View.VISIBLE
     if (isNetworkError) {
         view.visibility = View.GONE
     }
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url : String){
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}