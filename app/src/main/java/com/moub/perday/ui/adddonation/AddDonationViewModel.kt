package com.moub.perday.ui.adddonation

import android.content.ContentValues
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.tkafl.network.FirebaseRepository
import com.moub.perday.R
import com.moub.perday.model.DonationData
import com.moub.perday.model.DonationModel
import com.moub.perday.utils.Constants
import com.moub.perday.utils.toast

class AddDonationViewModel :ViewModel() {

    val isLoading = ObservableField(false)
    var isPhone = ObservableField(false)
    var isDescription = ObservableField(false)
    var isCity = ObservableField(false)
    var isCategory = ObservableField(false)
   // var isImage = ObservableField(false)
    fun saveEmployerData(
        phone: String,
         city:String,
         categories: String,
         image :String,
         description:String

    ) {

        val doc = FirebaseRepository.getInstance()!!.collection(Constants.Donation).document()
        val donationId = doc.id

        doc.set(DonationData(
            donationId,
            phone,
            city,
            categories,
            image,
            description
        )).addOnSuccessListener { Log.d("addDonation", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e) }
    }

    fun update(model: DonationModel, activity: AddDonationActivity) {
        isLoading.set(true)
        when {
            model.getPhone().isEmpty() -> {
                isLoading.set(false); isPhone.set(true)
            }
            model.getDescription().isEmpty() -> {
                isLoading.set(false); isDescription.set(true)
            }
            model.getcity().isEmpty() -> {
                isLoading.set(false); isCity.set(true)
            }
            model.getCategories().isEmpty() -> {
                isLoading.set(false); isCategory.set(true)
            }
           /* model.getImage().isEmpty() -> {
                isLoading.set(false); isImage.set(true)
            }*/
            else ->
            {
                val doc = FirebaseRepository.getInstance()!!.collection(Constants.Donation).document()
                val donationId = doc.id

                doc.set(DonationData(
                    donationId,
                    model.getPhone(),
                    model.getcity(),
                    model.getCategories(),
                    model.getImage(),
                    model.getDescription()
                )).addOnSuccessListener {
                    isLoading.set(false)
                    activity.getString(R.string.add_successful).toast(activity)
                    Log.d("addDonation", "DocumentSnapshot successfully written!") }

                    .addOnFailureListener {
                            e -> Log.w(ContentValues.TAG, "Error writing document", e)
                        isLoading.set(false)
                    }

            }
        }
    }
}