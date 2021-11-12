package com.moub.perday.ui.employee.applied
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.tkafl.network.FirebaseRepository

import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.moub.perday.model.CategoryData
import com.moub.perday.utils.Constants


class AppliedViewModel : ViewModel() {
    var savedAddresses : MutableLiveData<List<CategoryData>> = MutableLiveData()
    fun getSavedAddresses(): LiveData<List<CategoryData>>{
       FirebaseFirestore.getInstance().collection(Constants.Category).addSnapshotListener(EventListener{ value, e ->
            if (e != null) {
                savedAddresses.value = null
                return@EventListener
            }

            val savedCategoryList : MutableList<CategoryData> = mutableListOf()
            for (doc in value!!) {

                savedCategoryList.add(CategoryData(doc.getString("id"), doc.getString("name"),doc.getString("image")))
            }

            savedAddresses.value = savedCategoryList
        })



        return savedAddresses
    }


}