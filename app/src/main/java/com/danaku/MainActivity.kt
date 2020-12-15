package com.danaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    val database = FirebaseDatabase.getInstance()
    private var Daftarkontak = ArrayList<KontakModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        recyclerView = findViewById(R.id.datalist)
        supportActionBar!!.title = "KontakModel"


        MyRecyclerView()
        GetData()

    }
    private fun GetData(){
        Toast.makeText(applicationContext, "Mohon Tunggu Sebentar...",
            Toast.LENGTH_LONG).show()
        val getReference = database.getReference()
        getReference.child("admin").child("Daftarkontak")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(datasnapshot: DataSnapshot) {
                    if (datasnapshot.exists()) {
                        Daftarkontak.clear()
                        for (snapshot in datasnapshot.children) {
                            val teman = snapshot.getValue(KontakModel::class.java)
                            Log.e("lallll",teman.toString())
                            teman?.key = snapshot.key
                            Daftarkontak.add(teman!!)
                        }
                        adapter = RecyclerViewAdapter(Daftarkontak, this@MainActivity)
                        recyclerView?.adapter = adapter
                        (adapter as RecyclerViewAdapter).notifyDataSetChanged()
                        Toast.makeText(applicationContext, "Data berhasil dimuat",
                            Toast.LENGTH_LONG).show()
                    }
                }
                override  fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(applicationContext, "Data Ga sido dimuat",
                        Toast.LENGTH_LONG).show()
                    Log.e("MainActivity", databaseError.details + " " +
                            databaseError.message)
                }
            })
    }
    private fun MyRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.setHasFixedSize(true)
        val itemDecoration = DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL)


        itemDecoration.setDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.line)!!)

        recyclerView?.addItemDecoration(itemDecoration)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menubar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {


        R.id.action_favorite -> {
            var bundle = Bundle()
            bundle.putBoolean("isEdit",false)
            val intent = Intent(this, CreateActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}