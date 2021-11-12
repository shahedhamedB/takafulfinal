package com.moub.perday.ui.appliedform

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tkafl.network.FirebaseRepository
import com.moub.perday.R
import com.moub.perday.model.AppliedData
import com.moub.perday.model.AppliedModel
import com.moub.perday.utils.Constants
import com.moub.perday.utils.toast

class ApplidFormViewModel :ViewModel() {

    val isLoading = ObservableField(false)
    var isPhone = ObservableField(false)
    var isDescription = ObservableField(false)
    var isPasport = ObservableField(false)
    var isCategory = ObservableField(false)
    val status= MutableLiveData<Boolean>()


    fun update(model: AppliedModel, activity: AppliedFormActivity) {
        isLoading.set(true)

        when {
            model.getPhone().isEmpty() -> {
                isLoading.set(false); isPhone.set(true)
            }
            model.getDescription().isEmpty() -> {
                isLoading.set(false); isDescription.set(true)
            }
            model.getPasport().isEmpty() -> {
                isLoading.set(false); isPasport.set(true)
            }
            model.getCategories().isEmpty() -> {
                isLoading.set(false); isCategory.set(true)
            }
           /* model.getImage().isEmpty() -> {
                isLoading.set(false); isImage.set(true)
            }*/
            else ->
            {
                val doc = FirebaseRepository.getInstance()!!.collection(Constants.Applied).document()
                val donationId = doc.id

                doc.set(
                    AppliedData(
                    donationId,
                    model.getPhone(),
                    model.getPasport(),
                    model.getCategories(),
                    model.getImage(),
                    model.getDescription(),
                    "false",
                        "10",
                        "تكية ام علي "
                )
                ).addOnSuccessListener {
                    isLoading.set(false)
                    activity.getString(R.string.add_successful).toast(activity)
                    status.value=true
                    Log.d("addDonation", "DocumentSnapshot successfully written!") }

                    .addOnFailureListener {
                            e -> Log.w(ContentValues.TAG, "Error writing document", e)
                        isLoading.set(false)
                    }

            }
        }
    }
}