package com.moub.perday.model

import android.view.View
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.accepted_item.view.*
import kotlinx.android.synthetic.main.list_donation_detail.view.*

data class AppliedData (
    val id: String?,
    val phone: String?,
    val passport:String?,
    val categories: String?,
    val image :String?,
    val description:String?,
    val check:String?,
    val point:String?,
    val association:String?

        )
{
    companion object {
        @BindingAdapter("loadAppliedImage")
        @JvmStatic
        fun loadImage(view: View, image: String) {


            Glide.with(view.context)
                .load(image)
                .into(view.accept_image)
        }
    }}