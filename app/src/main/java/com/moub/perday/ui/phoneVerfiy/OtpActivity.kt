package com.moub.perday.ui.phoneVerfiy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.moub.perday.R
import com.moub.perday.ui.employee.main.MainActivity

import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.coroutines.MainScope
import java.util.concurrent.TimeUnit


class OtpActivity : AppCompatActivity() {
    private var verificationid: String? = null
    private var mAuth: FirebaseAuth? = null
    private var progressbar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        mAuth = FirebaseAuth.getInstance()

        progressbar = findViewById(R.id.progressbar)

        val phonenumber = intent.getStringExtra("phonenumber")
        if (phonenumber != null) {
            sendVerificationCode(phonenumber)
        }

        buttonSignIn.setOnClickListener(View.OnClickListener {
            val code = editTextCode.text.toString().trim { it <= ' ' }

            if (code.isEmpty() || code.length < 6) {

                editTextCode.error = "Enter code..."
                editTextCode.requestFocus()
                return@OnClickListener
            }
            verifyCode(code)
        })

        timer()
        ClickBtn.setOnClickListener {
            RegisterActivity.start(this)
            finish()
        }
    }

    fun timer() {
        object : CountDownTimer(60000, 1000) {

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                timerTxt.text = (millisUntilFinished / 1000).toString()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                timerTitle.visibility = View.GONE
                ClickBtn.visibility = View.VISIBLE
                timerTxt.text = "I did not receive the code!"
            }
        }.start()
    }

    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationid.toString(), code)
        signInWithCredential(credential)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        mAuth?.signInWithCredential(credential)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {

                val intent = Intent(this@OtpActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)


            } else {
                Toast.makeText(this@OtpActivity, task.exception!!.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun sendVerificationCode(number: String) {

        /*PhoneAuthProvider.getInstance().verifyPhoneNumber(
            number,
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallBack
        )*/
    }

    private val mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(p0!!, p1!!)
            verificationid = p0
        }

        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            val code = phoneAuthCredential.smsCode
            if (code != null) {
                progressbar?.visibility = View.VISIBLE
                verifyCode(code)
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(this@OtpActivity, e.message, Toast.LENGTH_LONG).show()

        }
    }

}
