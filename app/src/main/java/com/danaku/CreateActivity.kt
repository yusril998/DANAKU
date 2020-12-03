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
                val notelp  = findViewById<EditText>(R.id.notelep)
                val database = FirebaseDatabase.getInstance()

                val getnamalengkap: String = nama.getText().toString()
                val getnotlp: String = notelp.getText().toString()

                val getReference: DatabaseReference
                getReference = database.reference

                if (isEmpty(getnamalengkap) || isEmpty(getnotlp)) {
                    Toast.makeText(this, "DataTidak ada yang kosong",
                        Toast.LENGTH_SHORT).show()
                } else {
                    getReference.child("admin").child("Daftarnama").push()
                        .setValue(KontakModel(getnamalengkap, getnotlp))
                        .addOnCompleteListener {
                            nama.setText("")
                            notelp.setText("")
                            Toast.makeText(this, " Data Tersimpan" , Toast.LENGTH_SHORT).show()
                        }
                }

            }


        }

    }
}