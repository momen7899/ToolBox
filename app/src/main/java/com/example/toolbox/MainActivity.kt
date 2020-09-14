package com.example.toolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = ""

        Toast.makeText(this, getString(R.string.welcome), Toast.LENGTH_SHORT).show()

    }

    fun mainOnClick(view: View) {
        when (view.id) {
            R.id.main_pass -> {
                startActivity(Intent(this, PassActivity::class.java))
            }
            R.id.main_convertation -> {
                startActivity(Intent(this, ConvertationActivity::class.java))
            }
            R.id.main_flashlight -> {
                startActivity(Intent(this, FlashLightActivity::class.java))
            }
            R.id.main_timer -> {
                startActivity(Intent(this, TimerActivity::class.java))
            }
            R.id.main_calculator -> {
                startActivity(Intent(this, CalculatorActivity::class.java))
            }
            R.id.main_exit -> {
                Toast.makeText(this, getString(R.string.goodbyeText), Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }
}