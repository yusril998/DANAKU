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
    var k: String? = ""
    var stt: Boolean? = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
if (intent.extras!! .getBoolean("isEdit")){
    stt =intent.extras!! .getBoolean("isEdit")
    val nama  = findViewById<EditText>(R.id.nama)
    val telp  = findViewById<EditText>(R.id.notelp)
    val kantor = findViewById<EditText>(R.id.kantor)
    val email = findViewById<EditText>(R.id.email)
    val alamat = findViewById<EditText>(R.id.alamat)
    val fb = findViewById<EditText>(R.id.fb)
    val ig = findViewById<EditText>(R.id.ig)
    val wa = findViewById<EditText>(R.id.wa)
    val _nama = intent.extras!! .getString("nama")
    val _telp = intent.extras!! .getString("telp")
    val _kantor = intent.extras!! .getString("kantor")
    val _email = intent.extras!! .getString("email")
    val _alamat = intent.extras!! .getString("alamat")
    val _fb = intent.extras!! .getString("fb")
    val _ig = intent.extras!! .getString("ig")
    val _wa = intent.extras!! .getString("wa")
    nama.setText(_nama)
    telp.setText(_telp)
    kantor.setText(_kantor)
    email.setText(_email)
    alamat.setText(_alamat)
    fb.setText(_fb)
    ig.setText(_ig)
    wa.setText(_wa)
    k =  intent.extras!! .getString("key")
    val save = findViewById<Button>(R.id.simpan)

    save.setOnClickListener(this)
}else{
    stt =intent.extras!! .getBoolean("isEdit")
    val save = findViewById<Button>(R.id.simpan)

    save.setOnClickListener(this)
}


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
                    if (stt == true){
                        getReference.child("admin").child("Daftarkontak").child(k!!)
                            .setValue(KontakModel(getnamalengkap, getnotelp,  getkantor, getemail, getalamat, getfb, getig, getwa))
                            .addOnSuccessListener {
                                nama.setText("")
                                notelp.setText("")
                                kantor.setText("")
                                email.setText("")
                                alamat.setText("")
                                fb.setText("")
                                ig.setText("")
                                wa.setText("")
                                Toast.makeText(this, " Data Tersimpan" , Toast.LENGTH_SHORT).show()
                                finish()
                            }
                    }else{
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
                                finish()
                            }
                    }

                }

            }


        }

    }
}