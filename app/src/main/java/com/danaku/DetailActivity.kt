package com.danaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailActivity :AppCompatActivity(), View.OnClickListener {
    var k: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val _nama  = findViewById<TextView>(R.id.nama)
        val _telp  = findViewById<TextView>(R.id.notlp)
        val _kantor  = findViewById<TextView>(R.id.perusahaan)
        val _email  = findViewById<TextView>(R.id.email)
        val _fb  = findViewById<TextView>(R.id.fb)
        val _alamat  = findViewById<TextView>(R.id.alamat)
        val _ig  = findViewById<TextView>(R.id.ig)
        val _wa  = findViewById<TextView>(R.id.wa)
        val nama = intent.extras!! .getString("nama")
        val telp = intent.extras!! .getString("telp")
        val kantor = intent.extras!! .getString("kantor")
        val email = intent.extras!! .getString("email")
        val alamat = intent.extras!! .getString("alamat")
        val fb = intent.extras!! .getString("fb")
        val ig = intent.extras!! .getString("ig")
        val wa = intent.extras!! .getString("wa")
        _nama.setText(nama)
        _telp.setText(telp)
        _kantor.setText(kantor)
        _email.setText(email)
        _alamat.setText(alamat)
        _fb.setText(fb)
        _ig.setText(ig)
        _wa.setText(wa)
        k =  intent.extras!! .getString("key")

        val edit = findViewById<LinearLayout>(R.id.edit)
        edit.setOnClickListener(this)
        val hapus = findViewById<LinearLayout>(R.id.hapus)
        hapus.setOnClickListener(this)

    }
    override fun onClick (v: View){
        when (v.getId()){
            R.id.edit -> {
                val nama = intent.extras!! .getString("nama")
                val telp = intent.extras!! .getString("telp")
                val kantor = intent.extras!! .getString("kantor")
                val email = intent.extras!! .getString("email")
                val alamat = intent.extras!! .getString("alamat")
                val fb = intent.extras!! .getString("fb")
                val ig = intent.extras!! .getString("ig")
                val wa = intent.extras!! .getString("wa")
                var bundle = Bundle()
                bundle.putBoolean("isEdit",true)
                bundle.putString("nama", nama)
                bundle.putString("telp", telp)
                bundle.putString("kantor", kantor)
                bundle.putString("email", email)
                bundle.putString("alamat", alamat)
                bundle.putString("fb", fb)
                bundle.putString("ig", ig)
                bundle.putString("wa", wa)
                bundle.putString("key", k)

                val intent = Intent(this, CreateActivity::class.java)

                intent.putExtras(bundle)
                startActivity(intent)
                finish()

            }
            R.id.hapus -> {
                val database = FirebaseDatabase.getInstance()
                val getReference: DatabaseReference
                getReference = database.reference
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Apakah anda yakin menghapus data ini?")
                    .setCancelable(false)
                    .setPositiveButton("Ya") { dialog, id ->
                        getReference.child("admin").child("Daftarkontak").child(k!!)
                            .removeValue()
                            .addOnSuccessListener {

                                Toast.makeText(this, " Data berhasil dihapus" , Toast.LENGTH_SHORT).show()
                                finish()
                            }

                    }
                    .setNegativeButton("Tidak") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()



            }


        }

    }
    private val data: Unit
    private  get(){




    }
}