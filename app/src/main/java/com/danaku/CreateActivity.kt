package com.danaku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateActivity :AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        val save = findViewById<Button>(R.id.save)
        val show = findViewById<Button>(R.id.show)

        save.setOnClickListener(this)
        show.setOnClickListener(this)

    }
    override fun onClick (v: View){
        when (v.getId()){
            R.id.save -> {
                val namalengkap  = findViewById<EditText>(R.id.namalengkap)
                val namapanggilan  = findViewById<EditText>(R.id.namapanggilan)
                val database = FirebaseDatabase.getInstance()

                val getnamalengkap: String = namalengkap.getText().toString()
                val getnamapanggilan: String = namapanggilan.getText().toString()

                val getReference: DatabaseReference
                getReference = database.reference

                if (isEmpty(getnamalengkap) || isEmpty(getnamapanggilan)) {
                    Toast.makeText(this, "DataTidak ada yang kosong",
                        Toast.LENGTH_SHORT).show()
                } else {
//                    getReference.child("admin").child(getUserID).child("Daftarnama").push()
//                        .setValue(Daftarnama(getnamalengkap, getnamapanggilan))
//                        .addOnCompleteListener {
//                            namalengkap.setText("")
//                            namapanggilan.setText("")
//                            Toast.makeText(this, " Data Tersimpan" , Toast.LENGTH_SHORT).show()
//                        }
                }

            }

            R.id.show -> {
            }
        }

    }
}