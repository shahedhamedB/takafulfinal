package com.moub.perday.ui.phoneVerfiy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.moub.perday.R

import com.moub.perday.ui.employee.main.MainActivity
import kotlinx.android.synthetic.main.activity_register_phone.*

class RegisterActivity : AppCompatActivity() {
    private var editText: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_phone)


        spinnerCountries.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames)

        editText = findViewById(R.id.editTextPhone)

        buttonContinue.setOnClickListener(View.OnClickListener {
            //            val code = CountryData.countryAreaCodes[spinner?.selectedItemPosition!!]

            val number = editTextPhone.text.toString().trim { it <= ' ' }

            if (number.isEmpty() || number.length < 9) {
                editTextPhone.error = "Valid number is required"
                editTextPhone.requestFocus()
                return@OnClickListener
            }

            val phonenumber = "+962$number"

            val intent = Intent(this@RegisterActivity, OtpActivity::class.java)
            intent.putExtra("phonenumber", phonenumber)
            startActivity(intent)

        })

        anotherWayBtn.setOnClickListener {
           // RegisterEmployeeActivity.start(this)
            finish()
        }
    }


    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    private var exit: Boolean? = false
    override fun onBackPressed() {
        if (exit!!) {
            finish() // finish activity
        } else {

            exit = true
            Handler().postDelayed({ exit = false }, 3 * 1000)
        }
    }

}
