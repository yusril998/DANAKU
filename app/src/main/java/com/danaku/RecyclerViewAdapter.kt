package com.danaku

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val listDaftarkontak: ArrayList<KontakModel>, context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    private val context: Context

    inner class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Namalengkap: TextView

        val ListItem : LinearLayout
        init {
            Namalengkap = itemView.findViewById(R.id.namalengkap)

            ListItem = itemView.findViewById(R.id.list_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design,
            parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val Namalengkap: String? = listDaftarkontak.get(position).namalengkap
        val telp: String? = listDaftarkontak.get(position).notelp
        val kantor: String? = listDaftarkontak.get(position).kantor
        val email: String? = listDaftarkontak.get(position).email
        val alamat: String? = listDaftarkontak.get(position).alamat
        val fb: String? = listDaftarkontak.get(position).fb
        val ig: String? = listDaftarkontak.get(position).ig
        val wa: String? = listDaftarkontak.get(position).wa
        val key: String? = listDaftarkontak.get(position).key


        holder.Namalengkap.text = Namalengkap
        holder.ListItem.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                var bundle = Bundle()
                bundle.putString("nama", Namalengkap)
                bundle.putString("telp", telp)
                bundle.putString("kantor", kantor)
                bundle.putString("email", email)
                bundle.putString("alamat", alamat)
                bundle.putString("fb", fb)
                bundle.putString("ig", ig)
                bundle.putString("wa", wa)
                bundle.putString("key", key)

                val intent = Intent(view?.context, DetailActivity::class.java)

                intent.putExtras(bundle)
                context.startActivity(intent)

            }
        })

    }
    override fun getItemCount(): Int {
        return listDaftarkontak.size
    }
    init{
        this.context = context
    }
}