package com.moub.perday.ui.appliedform

import android.Manifest
import android.app.*
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.storage.FirebaseStorage
import com.moub.perday.R
import com.moub.perday.databinding.ActivityAppliedFormBinding
import com.moub.perday.model.AppliedModel
import java.util.*


class AppliedFormActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAppliedFormBinding
    private var selectedPhotoUri: Uri? = null
    private val PERMISSION_CODE = 1000
    private val IMAGE_CAPTURE_CODE = 1001
    private var logo: String = ""
    var Image1Ready: Boolean = false
    private var image: String = ""
    private lateinit var applidFormViewModel: ApplidFormViewModel
     var notificationManager: NotificationManager?=null
    private var model = AppliedModel(

        "",
        "",
        "",
        "",
        ""
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_applied_form)
        instance = this
        applidFormViewModel = ViewModelProvider(this).get(ApplidFormViewModel::class.java)
        binding.let { it.viewModel = applidFormViewModel; it.view = this; it.model = model }

        binding.imgDonation.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED
                ) {
                    //permission was not enabled
                    val permission = arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    //show popup to request permission
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    //permission already granted
                    showPictureDialog()
                }
            } else {
                //system os is < marshmallow
                showPictureDialog()
            }

        }

        binding.spinnerCategory.apply {
            setOnSpinnerOutsideTouchListener { _, motionEvent ->
                motionEvent.action.and(0)
                dismiss()
            }
            try {
                lifecycleOwner = this@AppliedFormActivity
            } catch (e: Exception) {
            }
            setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newText ->

                model.setCategories(categories = "$text")
            }


        }
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        applidFormViewModel.status.observe(this, {
            if (it == true) {
                btnNotify()
            }
        })
    }
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AppliedFormActivity::class.java)
            context.startActivity(intent)
        }
        lateinit var instance: AppliedFormActivity

    }
    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Take Photo")
        pictureDialog.setItems(
            pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> openCamera()
            }
        }

        pictureDialog.show()
    }
    private fun choosePhotoFromGallary() {

        Log.d("", "Try to show photo selector")
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
        Toast.makeText(this, " please upload photo   ", Toast.LENGTH_SHORT)
            .show()
    }


    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        selectedPhotoUri =
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedPhotoUri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("", "Photo was selected")

            selectedPhotoUri = data.data

            try {
                binding.imgDonation.setImageURI(selectedPhotoUri)
            } catch (e: Exception) {
            }
            uploadImageToFirebase {}
            Toast.makeText(this, " upload success ", Toast.LENGTH_SHORT).show()
        } else if (requestCode == IMAGE_CAPTURE_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //set image captured to image view
            selectedPhotoUri = data.data
            uploadImageToFirebase {}

        }


    }
    private fun uploadImageToFirebase(complete: () -> Unit) {
        if (selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("", "Successfully uploaded image: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    Log.d("", "File Location: $it")
                    Image1Ready = true
                    this.image = it.toString()
                    this.logo = it.toString()
                    complete()
                    model.setImage(this.image)
                }



            }
            .addOnFailureListener {
                Log.d("", "Failed to upload image to storage: ${it.message}")
                // AppLogger.toast(this, it.message.toString())

            }

    }
    fun btnNotify() {

        //builder.setSmallIcon(R.drawable.ic_launcher_background)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.h1) //set icon for notification
            .setContentTitle("???? ?????????? ???????? ") //set title of notification
            .setContentText("???? ?????????? ?????? ?????????????? ?????????? ?????????????? ???????????? ?????????? ??????????????") //this is notification message
            .setAutoCancel(true) // makes auto cancel of notification
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //set priority of notification


        val notificationIntent = Intent(this, AppliedFormActivity::class.java)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(pendingIntent)

        // Add as notification

        // Add as notification
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder.build())
    }

}