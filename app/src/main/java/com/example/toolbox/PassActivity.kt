package com.example.toolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_pass.*

class PassActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseOpenHelper
    private lateinit var adapter: PassAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass)

        dbHelper = DatabaseOpenHelper(this)
        adapter = PassAdapter(this, dbHelper.getPassList())

        pass_recycle.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        pass_recycle.adapter = adapter

        pass_add.setOnClickListener { _ ->
            val intent = Intent(this, AddEditPassActivity::class.java)
            intent.putExtra("sit", 0)
            startActivity(intent)
        }


    }

    override fun onRestart() {
        adapter.setList(dbHelper.getPassList())
        super.onRestart()
    }
}