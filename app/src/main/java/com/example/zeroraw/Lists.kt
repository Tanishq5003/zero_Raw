package com.example.zeroraw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class Lists : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var userArrayList: ArrayList<recylerarray>
    lateinit var database: DatabaseReference
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lists)

        recyclerView = findViewById(R.id.recycle)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userArrayList = arrayListOf()
        auth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance().getReference(Firebase.auth.currentUser!!.uid)
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (datasnapshot in snapshot.children){
                        val reclerarray = datasnapshot.getValue(recylerarray::class.java)
                        if (!userArrayList.contains(reclerarray)){
                            userArrayList.add(reclerarray!!)
                        }
                    }
                    recyclerView.adapter = MyAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Lists, error.toString(), Toast.LENGTH_SHORT).show()
            }

        })

    }
}