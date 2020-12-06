package com.danaku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateActivity :AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val save = findViewById<LinearLayout>(R.id.simpan)

        save.setOnClickListener(this)

    }
    override fun onClick (v: View){
        when (v.getId()){
            R.id.simpan -> {
                val nama  = findViewById<EditText>(R.id.nama)
                val notelp  = findViewById<EditText>(R.id.notelp)
                val kantor = findViewById<EditText>(R.id.kantor)
                val email = findViewById<EditText>(R.id.email)
                val alamat = findViewById<EditText>(R.id.alamat)
                val fb = findViewById<EditText>(R.id.fb)
                val ig = findViewById<EditText>(R.id.ig)
                val wa = findViewById<EditText>(R.id.wa)
                val database = FirebaseDatabase.getInstance()

                val getnamalengkap: String = nama.getText().toString()
                val getnotelp: String = notelp.getText().toString()
                val getkantor: String =  kantor.getText().toString()
                val getemail: String = email.getText().toString()
                val getalamat: String = alamat.getText().toString()
                val getfb: String = fb.getText().toString()
                val getig: String = ig.getText().toString()
                val getwa: String = wa.getText().toString()
                val getReference: DatabaseReference
                getReference = database.reference

                if (isEmpty(getnamalengkap) || isEmpty(getnotelp)) {
                    Toast.makeText(this, "DataTidak ada yang kosong",
                        Toast.LENGTH_SHORT).show()
                } else {
                    getReference.child("admin").child("Daftarkontak").push()
                        .setValue(KontakModel(getnamalengkap, getnotelp,  getkantor, getemail, getalamat, getfb, getig, getwa))
                        .addOnCompleteListener {
                            nama.setText("")
                            notelp.setText("")
                            kantor.setText("")
                            email.setText("")
                            alamat.setText("")
                            fb.setText("")
                            ig.setText("")
                            wa.setText("")
                            Toast.makeText(this, " Data Tersimpan" , Toast.LENGTH_SHORT).show()
                        }
                }

            }


        }

    }
}