package com.moub.perday.ui.employee.associations

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moub.perday.R
import com.moub.perday.model.AppliedData
import com.moub.perday.ui.acceptapplay.AcceptApplayActivity
import com.moub.perday.ui.acceptapplay.AcceptApplayViewModel

class AssociationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_association)
    }
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AssociationActivity::class.java)
            context.startActivity(intent)
        }


    }
}