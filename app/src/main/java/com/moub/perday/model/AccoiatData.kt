package com.moub.perday.model

import android.view.View
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_category.view.*

data class AccoiatData(
    val id: String?,
    val name: String?,
    val image:String?,
    val phone:String?
    ){
    companion object {
        @BindingAdapter("loadAssoiatImage")
        @JvmStatic
        fun loadImage(view: View, image: String) {
            Glide.with(view.context)
                .load(image)
                .into(view.image_category)
        }
    }
}
