package com.moub.perday.ui.acceptapplay
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.moub.perday.model.AppliedData
import com.moub.perday.utils.Constants


class AcceptApplayViewModel : ViewModel() {
    var savedAddresses : MutableLiveData<List<AppliedData>> = MutableLiveData()
    fun getSavedAddresses(): LiveData<List<AppliedData>>{
       FirebaseFirestore.getInstance().collection(Constants.Applied).
       addSnapshotListener(EventListener{ value, e ->
            if (e != null) {
                savedAddresses.value = null
                return@EventListener
            }

            val savedCategoryList : MutableList<AppliedData> = mutableListOf()
            for (doc in value!!) {

                savedCategoryList.add(AppliedData(doc.getString("id"), doc.getString("phone"),doc.getString("passport")
                        ,doc.getString("categories"), doc.getString("image"),doc.getString("description"),doc.getString("check"),
                    doc.getString("point"),doc.getString("association")))



            }

            savedAddresses.value = savedCategoryList
        })



        return savedAddresses
    }



}