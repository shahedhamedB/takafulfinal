package com.moub.perday.ui.donationapllied
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
import com.moub.perday.model.AppliedData
import com.moub.perday.model.CategoryData
import com.moub.perday.model.DonationData
import com.moub.perday.ui.employee.main.MainActivity
import com.moub.perday.utils.Constants
import com.moub.perday.utils.toast


class DonationappliedDetailViewModel : ViewModel() {
    var savedAddresses : MutableLiveData<List<AppliedData>> = MutableLiveData()
    fun getSavedAddresses(categories: String): LiveData<List<AppliedData>>{
       FirebaseFirestore.getInstance().collection(Constants.Applied).whereEqualTo("categories", categories).
       addSnapshotListener(EventListener{ value, e ->
            if (e != null) {
                savedAddresses.value = null
                return@EventListener
            }

            val savedCategoryList : MutableList<AppliedData> = mutableListOf()
            for (doc in value!!) {


                savedCategoryList.add(AppliedData(doc.getString("id"), doc.getString("phone"),doc.getString("passport")
                        ,doc.getString("categories"), doc.getString("image"),doc.getString("description"),
                    doc.getString("check"), doc.getString("point"),doc.getString("association")))



            }

            savedAddresses.value = savedCategoryList
        })



        return savedAddresses
    }



}