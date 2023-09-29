package com.example.zeroraw

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.File

class selectedListing : AppCompatActivity() {
    lateinit var addres_text: TextView
    lateinit var basement_text: TextView
    lateinit var furnishing_text: TextView
    lateinit var parking_text: TextView
    lateinit var type_text: TextView
    lateinit var total_text: TextView
    lateinit var carpet_text: TextView
    lateinit var buildup_text: TextView
    lateinit var floors_text: TextView
    lateinit var bedroom_text: TextView
    lateinit var bathroom_text: TextView
    lateinit var balcony_text: TextView
    lateinit var extra_text: TextView
    lateinit var price_text: TextView
    lateinit var photo1: ImageView
    lateinit var photo2: ImageView
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private  var storageRef = Firebase.storage
    lateinit var delete: Button
    lateinit var propertyData: ArrayList<data>
    var price: String? = null
    var address: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_listing)
        val bundle: Bundle? = intent.extras
        storageRef = FirebaseStorage.getInstance()
        photo1 = findViewById(R.id.photo1)
        photo2 = findViewById(R.id.photo2)
        delete = findViewById(R.id.delete)
        addres_text = findViewById(R.id.address)
        price_text = findViewById(R.id.price)
        total_text = findViewById(R.id.total)
        carpet_text = findViewById(R.id.carpet)
        buildup_text = findViewById(R.id.buildup)
        floors_text = findViewById(R.id.floors)
        bedroom_text = findViewById(R.id.bedroom)
        bathroom_text = findViewById(R.id.bathroom)
        balcony_text = findViewById(R.id.balcony)
        basement_text = findViewById(R.id.basement)
        furnishing_text = findViewById(R.id.furnish)
        parking_text = findViewById(R.id.parking)
        type_text = findViewById(R.id.type)
        extra_text = findViewById(R.id.extra)
        auth = FirebaseAuth.getInstance()
//        database = Firebase.database.reference
        address= bundle!!.getString("address")
        addres_text.text = address
        propertyData = arrayListOf()

        database = FirebaseDatabase.getInstance().getReference(auth.currentUser!!.uid)
//        val parRef = database.ref.child(auth.currentUser!!.uid)
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (dataSnapshot in snapshot.children){
                        if (dataSnapshot.child("address").getValue(String::class.java)==address){
                            val data = dataSnapshot.getValue(data::class.java)
                            if (!propertyData.contains(data)){
                                propertyData.add(data!!)
                            }
                        }
                    }
                    try {
                        val data = propertyData[0]
                        price_text.text = data.price
                        total_text.text = data.area
                        carpet_text.text = data.carpet
                        buildup_text.text = data.buildup
                        floors_text.text = data.floors
                        bedroom_text.text = data.bedroom
                        bathroom_text.text = data.bathroom
                        balcony_text.text = data.balcony
                        basement_text.text = data.basement
                        furnishing_text.text = data.furnished
                        parking_text.text = data.parking
                        type_text.text = data.type
                        extra_text.text = data.extra
                    }
                    catch (e: IndexOutOfBoundsException){
                        Toast.makeText(this@selectedListing, "Error", Toast.LENGTH_SHORT).show()
                    }


                }
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        val localFile: File = File.createTempFile("tempfile", ".jpg")
        val storageReference = FirebaseStorage.getInstance().getReference(auth.currentUser!!.uid+"/"+address+"image1")
        storageReference.getMetadata().addOnSuccessListener {
            // The file exists
            storageReference.getFile(localFile).addOnSuccessListener {
                // The file has been downloaded successfully
                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                photo1.setImageBitmap(bitmap)
            }
                .addOnFailureListener {
                    // An error occurred while downloading the file
                }
        }
            .addOnFailureListener {
                // The file does not exist
            }

        val localFile1: File = File.createTempFile("tempfile1", ".jpg")
        val storageReference1 = FirebaseStorage.getInstance().getReference(auth.currentUser!!.uid+"/"+address+"image2")
        storageReference1.getMetadata().addOnSuccessListener {
            // The file exists
            storageReference1.getFile(localFile1).addOnSuccessListener {
                // The file has been downloaded successfully
                val bitmap1 = BitmapFactory.decodeFile(localFile1.absolutePath)
                photo2.setImageBitmap(bitmap1)
            }
                .addOnFailureListener {
                    // An error occurred while downloading the file
                }
        }
            .addOnFailureListener {
                // The file does not exist
            }

        delete.setOnClickListener {
            database.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (dataSnapshot in snapshot.children) {
                            if (dataSnapshot.child("address")
                                    .getValue(String::class.java) == address
                            ) {
                               val snap = dataSnapshot.ref
                                snap.removeValue()
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
            val intent = Intent(this, FirstPage::class.java)
            startActivity(intent)
        }



    }
}