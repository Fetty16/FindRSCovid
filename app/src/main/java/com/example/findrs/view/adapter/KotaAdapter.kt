package com.example.findrs.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.findrs.R
import com.example.findrs.model.kota.ModelKotaResult.ModelKota
import com.example.findrs.utils.Constant
import com.example.findrs.view.activities.HospitalsActivity
import com.example.findrs.view.adapter.KotaAdapter.KotaViewHolder
import kotlinx.android.synthetic.main.list_item_kota.view.*
import java.util.*

class KotaAdapter(private val context: Context) : RecyclerView.Adapter<KotaViewHolder>() {

    private val modelKotaArrayList = ArrayList<ModelKota>()

    fun setKotaAdapter(items: ArrayList<ModelKota>) {
        modelKotaArrayList.clear()
        modelKotaArrayList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_kota, parent, false)
        return KotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: KotaViewHolder, position: Int) {

        //set data to share & intent
        holder.tvKota.text = modelKotaArrayList[position].name

        holder.cvDaftarKota.setOnClickListener {
            Constant.kotaId = modelKotaArrayList[position].id
            Constant.kotaName = modelKotaArrayList[position].name
            val intent = Intent(context, HospitalsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelKotaArrayList.size
    }

    class KotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvDaftarKota: CardView
        var tvKota: TextView

        init {
            cvDaftarKota = itemView.cvDaftarKota
            tvKota = itemView.tvKota
        }
    }

}