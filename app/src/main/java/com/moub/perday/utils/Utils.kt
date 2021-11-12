package com.moub.perday.utils

import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.moub.perday.ui.adddonation.AddDonationActivity
import com.moub.perday.ui.appliedform.AppliedFormActivity
import com.moub.perday.ui.employee.donation.DonationFragment
import com.moub.perday.ui.employee.main.MainActivity

fun String.toast(context: AppliedFormActivity, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this, duration).apply { show() }
}
fun String.toast(context: AddDonationActivity, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this, duration).apply { show() }


}
fun String.toast(context: MainActivity, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this, duration).apply { show() }
}

    fun Context.start(to: AppCompatActivity) {
        val intent = Intent(applicationContext, to::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }