package com.example.toolbox

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class PassAdapter(
    val context: Context,
    var passList: ArrayList<Password>
) : RecyclerView.Adapter<PassAdapter.PassViewHolder>() {

    class PassViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val appName: TextView = view.findViewById(R.id.pass_item_app_name)
        val passId: TextView = view.findViewById(R.id.pass_item_id)
        val layout: ConstraintLayout = view.findViewById(R.id.pass_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassViewHolder =
        PassViewHolder(LayoutInflater.from(context).inflate(R.layout.pass_item, parent, false))

    override fun getItemCount(): Int = passList.size

    override fun onBindViewHolder(holder: PassViewHolder, position: Int) {
        holder.appName.text = passList[position].appName
        holder.passId.text = passList[position].id.toString()
        holder.layout.setOnClickListener { _ ->
            val intent = Intent(context, AddEditPassActivity::class.java)
            intent.putExtra("sit", passList[position].id)
            context.startActivity(intent)
        }
    }

    fun setList(list: ArrayList<Password>) {
        passList = list
        notifyDataSetChanged()
    }

}