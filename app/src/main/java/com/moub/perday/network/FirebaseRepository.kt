package com.example.tkafl.network


import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.moub.perday.model.AppliedData
import com.moub.perday.model.DonationData
import com.moub.perday.utils.Constants

object FirebaseRepository{
   var fireStore:FirebaseFirestore? =null
    private var mAuth: FirebaseAuth? = null

    fun getInstance(): FirebaseFirestore? {
        if (fireStore == null) {
           fireStore= FirebaseFirestore.getInstance()
        }
        return fireStore
    }
    fun getmAuth(): FirebaseAuth? {
        if (mAuth == null) mAuth = FirebaseAuth.getInstance()
        return mAuth
    }
    // get saved addresses from firebase

    fun saveEmployerData(
         id: String,
         phone: String,
         passport:String,
         categories: String,
         image :String,
         description:String,
         check:String,
         point:String,
        association:String

    ) {

        val doc = FirebaseRepository.getInstance()!!.collection(Constants.Applied).document()


        doc.set(
            AppliedData(
                id,
                phone,
                passport,
                categories,
                image ,
                description,
                check,
                point,
                association

            )
        ).addOnSuccessListener { Log.d("addDonation", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e) }
    }








}