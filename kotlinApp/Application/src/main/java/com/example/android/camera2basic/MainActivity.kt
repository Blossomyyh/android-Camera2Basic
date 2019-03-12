package com.example.android.camera2basic

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(){
    private val RESULT_PERMISSIONS = 0x9000

    val REQUEST_PERMISSIONS = arrayOf( Manifest.permission.CAMERA)

    val isPermission : Boolean
        get() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                ActivityCompat.requestPermissions(this@MainActivity,
                        REQUEST_PERMISSIONS, RESULT_PERMISSIONS)
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
                return false
            } else{
                return true
            }
            return false
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.permissionButtion).setOnClickListener {
            if(isPermission){
                startQRActivity()
            }
        }
    }

    private fun startQRActivity() {
        startActivity(Intent(this,CameraActivity::class.java))
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(RESULT_PERMISSIONS == requestCode){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                startQRActivity()
            } else {
                // Rejected
                Toast.makeText(this, R.string.err_permission_not_granted, Toast.LENGTH_SHORT).show()
            }
            return
        }
    }


}