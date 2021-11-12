package com.moub.perday.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.moub.perday.App
import com.moub.perday.R

data class AppliedModel (
    private var phone: String, private var pasport: String,
    private var categories: String,private var image :String,
    private var description:String) : BaseObservable()
        {
            @Bindable
            fun setPhone(phone: String) {
                this.phone = phone

            }
            @Bindable
            fun getPhone(): String {
                return phone
            }

            @Bindable
            fun setPasport(pasport: String) {
                this.pasport = pasport

            }
            @Bindable
            fun getCategories(): String {
                return categories
            }
            @Bindable
            fun setCategories(categories: String) {
                this.categories = categories

            }
            @Bindable
            fun getPasport(): String {
                return pasport
            }
            @Bindable
            fun setImage(image: String) {
                this.image = image

            }
            @Bindable
            fun getImage(): String {
                return image
            }
            @Bindable
            fun setDescription(description: String) {
                this.description = description

            }
            @Bindable
            fun getDescription(): String {
                return description
            }

            @Bindable
            fun getPhoneError(): String? {
                return if (phone.isEmpty()) {
                    App.instance.getString(R.string.phone_empty_error)
                } else {
                    null
                }
            }
            @Bindable
            fun getPasportError(): String? {
                return if (pasport.isEmpty()) {
                    App.instance.getString(R.string.passport_empty_error)
                } else {
                    null
                }
            }
            @Bindable
            fun getCategoriesError(): String? {
                return if (categories.isEmpty()) {
                    App.instance.getString(R.string.categories_empty_error)
                } else {
                    null
                }
            }
            @Bindable
            fun getImageError(): String? {
                return if (image.isEmpty()) {
                    App.instance.getString(R.string.image_empty_error)
                } else {
                    null
                }
            }
            @Bindable
            fun getDescriptionError(): String? {
                return if (description.isEmpty()) {
                    App.instance.getString(R.string.description_empty_error)
                } else {
                    null
                }
            }


        }