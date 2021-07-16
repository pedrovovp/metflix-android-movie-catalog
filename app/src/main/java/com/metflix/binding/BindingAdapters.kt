package com.metflix.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.metflix.data_remote.AppService.Companion.IMAGE_PATH_ORIGINAL
import com.metflix.data_remote.AppService.Companion.IMAGE_PATH_W500

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageFromUrlReduced")
    fun bindImageFromUrlReduced(image: ImageView, url: String?) {
        Glide.with(image.context)
            .load(IMAGE_PATH_W500 + url)
            .into(image)
    }

    @JvmStatic
    @BindingAdapter("imageFromUrlOriginal")
    fun bindImageFromUrlOriginal(image: ImageView, url: String?) {
        Glide.with(image.context)
            .load(IMAGE_PATH_ORIGINAL + url)
            .into(image)
    }
}