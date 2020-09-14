package com.example.toolbox

import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_edit_pass.*

class AddEditPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_pass)

        val id = intent.getIntExtra("sit", -1)
        val dbHelper = DatabaseOpenHelper(this)
        add_edit_pass_title.text = "اضافه کردن رمز جدید"
        if (id > 0) {
            init(dbHelper.getPass(id))
            add_edit_pass_title.text = "ویرایش رمز"
        }

        add_edit_pass_submit.setOnClickListener {
            val name = add_edit_pass_name.text.toString().trim()
            val pass = add_edit_pass_pass.text.toString().trim()
            println(name + "\t" + pass)
            val password = Password(name, id, pass)
            if (id > 0) dbHelper.updatePass(password)
            else dbHelper.addPass(password)
            finish()
        }

        pass_visible.setOnClickListener { _ ->
            if (pass_visible.isChecked) {
                add_edit_pass_pass.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
            } else {
                add_edit_pass_pass.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
    }

    private fun init(pass: Password) {
        add_edit_pass_name.setText(pass.appName)
        add_edit_pass_pass.setText(pass.pass)
    }
}