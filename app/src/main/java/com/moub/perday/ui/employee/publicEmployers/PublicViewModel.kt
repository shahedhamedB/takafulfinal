package com.moub.perday.ui.employee.publicEmployers
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.tkafl.network.FirebaseRepository

import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.moub.perday.model.AccoiatData
import com.moub.perday.model.CategoryData
import com.moub.perday.ui.employee.main.MainActivity
import com.moub.perday.utils.Constants
import com.moub.perday.utils.toast


class PublicViewModel : ViewModel() {
    var savedAddresses : MutableLiveData<List<AccoiatData>> = MutableLiveData()
    fun getSavedAddresses(): LiveData<List<AccoiatData>>{
       FirebaseFirestore.getInstance().collection(Constants.Assioat).addSnapshotListener(EventListener{ value, e ->
            if (e != null) {
                savedAddresses.value = null
                return@EventListener
            }

            val savedCategoryList : MutableList<AccoiatData> = mutableListOf()
            for (doc in value!!) {

                savedCategoryList.add(AccoiatData(doc.getString("id"), doc.getString("name"),doc.getString("image"),doc.getString("phone")))
            }

            savedAddresses.value = savedCategoryList
        })



        return savedAddresses
    }



}