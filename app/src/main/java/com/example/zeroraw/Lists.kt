package com.example.zeroraw

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class Lists : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var userArrayList: ArrayList<recylerarray>
    lateinit var database: DatabaseReference
    lateinit var auth: FirebaseAuth
    private var bitmap: Bitmap? = null
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
                    val addres: String? = userArrayList[0].address.toString()
                    val imageName: String? = addres+"image1"
                    val storageReference = FirebaseStorage.getInstance().getReference(auth.currentUser!!.uid+"/"+imageName)
                    val localFile: File = File.createTempFile("tempfile", ".jpg")
                    storageReference.getFile(localFile).addOnSuccessListener {
                        bitmap= BitmapFactory.decodeFile(localFile.absolutePath)
                    }
                    val bitmap1: Bitmap? = bitmap

                    var adapter = MyAdapter(userArrayList)
                    recyclerView.adapter = adapter
                adapter.setonItemClickListner(object : MyAdapter.onItemClickListner{
                    override fun onItemClick(pos: Int) {
//                        Toast.makeText(this@Lists, "Clicked on ${pos+1}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Lists, selectedListing::class.java)
                        intent.putExtra("address", addres)
                        startActivity(intent)
                    }

                })}
                }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Lists, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })



    }
}