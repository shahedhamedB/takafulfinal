package com.moub.perday.ui.splash

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.dynamiclinks.ShortDynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.shortLinkAsync
import com.google.firebase.ktx.Firebase
import com.moub.perday.R
import com.moub.perday.ui.employee.main.MainActivity
import zendesk.chat.Chat
import zendesk.chat.ChatConfiguration
import zendesk.chat.ChatEngine
import zendesk.messaging.MessagingActivity


class SplashActivity : AppCompatActivity() {


    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //3 seconds
    var TAG="main"
    var webView:WebView?=null

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
       mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }




}



