package com.moub.perday.model

import android.view.View
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_donation_detail.view.*

data class DonationData (
    val id: String?,
    val phone: String?,
    val city:String?,
    val categories: String?,
    val image :String?,
    val description:String?
        )
{
    companion object {
        @BindingAdapter("loadDonationImage")
        @JvmStatic
        fun loadImage(view: View, image: String) {


            Glide.with(view.context)
                .load(image)
                .into(view.detail_image)
        }
    }
}