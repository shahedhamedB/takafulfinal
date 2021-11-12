package com.moub.perday.ui.donationdetail
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.tkafl.network.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.moub.perday.model.CategoryData
import com.moub.perday.model.DonationData
import com.moub.perday.ui.employee.main.MainActivity
import com.moub.perday.utils.Constants
import com.moub.perday.utils.toast


class DonationDetailViewModel : ViewModel() {
    var savedAddresses : MutableLiveData<List<DonationData>> = MutableLiveData()
    fun getSavedAddresses(categories: String): LiveData<List<DonationData>>{
       FirebaseFirestore.getInstance().collection(Constants.Donation).whereEqualTo("categories", categories).
       addSnapshotListener(EventListener{ value, e ->
            if (e != null) {
                savedAddresses.value = null
                return@EventListener
            }

            val savedCategoryList : MutableList<DonationData> = mutableListOf()
            for (doc in value!!) {

                savedCategoryList.add(DonationData(doc.getString("id"), doc.getString("phone"),doc.getString("city")
                        ,doc.getString("categories"), doc.getString("image"),doc.getString("description")))



            }

            savedAddresses.value = savedCategoryList
        })



        return savedAddresses
    }



}