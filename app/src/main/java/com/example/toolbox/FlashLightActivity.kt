package com.example.toolbox

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_flash_light.*
import java.lang.Exception


class FlashLightActivity : AppCompatActivity() {

    private lateinit var mCameraManager: CameraManager
    private lateinit var mCameraId: String

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_light)

        val isFlashAvailable = applicationContext.packageManager
            .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        if (!isFlashAvailable) {
            showNoExist();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mCameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
            try {
                mCameraId = mCameraManager.cameraIdList[0]
                mCameraManager.setTorchMode(mCameraId, toggleButton.isChecked)
            } catch (@SuppressLint("NewApi") e: CameraAccessException) {
                e.printStackTrace()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    mCameraManager.setTorchMode(mCameraId, isChecked)
                } catch (e: CameraAccessException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun showNoExist() {
        val alert: android.app.AlertDialog? = android.app.AlertDialog.Builder(this).create()
        alert?.setTitle(getString(R.string.warn))
        alert?.setMessage(getString(R.string.notExistFlashLight))
        alert?.setButton(
            DialogInterface.BUTTON_POSITIVE,
            getString(R.string.getIt)
        ) { _, _ -> finish() }
        alert?.show()
    }
}