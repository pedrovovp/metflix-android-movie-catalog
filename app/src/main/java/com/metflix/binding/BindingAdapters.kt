package com.metflix.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.metflix.data_remote.AppService.Companion.IMAGE_PATH

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageFromUrl")
    fun bindImageFromUrl(image: ImageView, url: String?) {
        Glide.with(image.context)
            .load(IMAGE_PATH + url)
            .into(image)
    }
}