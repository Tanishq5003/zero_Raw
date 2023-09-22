package com.example.zeroraw

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.util.ArrayList
import kotlin.random.Random

@Suppress("DEPRECATION")
class FinalReview : AppCompatActivity() {
    lateinit var photo1: ImageView
    lateinit var photo2: ImageView
    lateinit var address_text: TextView
    lateinit var price_text: TextView
    lateinit var total_text: TextView
    lateinit var carpet_text: TextView
    lateinit var buildup_text: TextView
    lateinit var floors_text: TextView
    lateinit var bedroom_text: TextView
    lateinit var bathroom_text: TextView
    lateinit var balcony_text: TextView
    lateinit var basement_text: TextView
    lateinit var furnishing_text: TextView
    lateinit var parking_text: TextView
    lateinit var type_text: TextView
    lateinit var extra_text: TextView
    lateinit var submit: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private  var storageRef = Firebase.storage
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_review)
        storageRef = FirebaseStorage.getInstance()
        photo1 = findViewById(R.id.photo1)
        photo2 = findViewById(R.id.photo2)
        submit = findViewById(R.id.submit)
        address_text = findViewById(R.id.address)
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
        database = Firebase.database.reference

        val intentFromDetails: Intent = intent
        val photoOne: Uri? = intentFromDetails.getParcelableExtra<Uri>("photo1", )
        photo1.setImageURI(photoOne)
        val photoTwo = intentFromDetails.getParcelableExtra<Uri>("photo2", )
        photo2.setImageURI(photoTwo)



        val basement: String? = intentFromDetails.getStringExtra("Basement")
        basement_text.text = basement
        bathroom_text.text = intentFromDetails.getStringExtra("nbath")
        bedroom_text.text = intentFromDetails.getStringExtra("nbes")
        balcony_text.text = intentFromDetails.getStringExtra("nbal")
        address_text.text = intentFromDetails.getStringExtra("Address")
        price_text.text = intentFromDetails.getStringExtra("Price")
        furnishing_text.text = intentFromDetails.getStringExtra("Furnished")
        type_text.text = intentFromDetails.getStringExtra("Property Type")
        parking_text.text = intentFromDetails.getStringExtra("Parking")
        floors_text.text = intentFromDetails.getStringExtra("Floors")
        total_text.text = intentFromDetails.getStringExtra("Total Area")
        carpet_text.text = intentFromDetails.getStringExtra("Carpet Area")
        buildup_text.text = intentFromDetails.getStringExtra("Buildup Area")

        val extraRooms: ArrayList<String>? = intentFromDetails.getStringArrayListExtra("Rooms")
        if (extraRooms != null) {
            for ((index, room) in extraRooms.withIndex()) {
                val isNotLast = index < extraRooms.size-1
                if (isNotLast){
                    extra_text.text = extra_text.text.toString() + "$room; "
                }
                else{
                    extra_text.text = extra_text.text.toString() + "$room"
                }
            }
        }
        else{
            extra_text.text = "Null"
        }
        submit.setOnClickListener {
//            database.child(Firebase.auth.currentUser!!.uid).child("Address").setValue(address_text.text.toString())
//            database.child(Firebase.auth.currentUser!!.uid).child("Address")
//                .child("Property Type").setValue(type_text.text.toString())

            val property = data(address_text.text.toString(), type_text.text.toString(), total_text.text.toString(),
                carpet_text.text.toString(), buildup_text.text.toString(), floors_text.text.toString(),
                bedroom_text.text.toString(), bathroom_text.text.toString(), balcony_text.text.toString(),
                extra_text.text.toString(), basement_text.text.toString(), furnishing_text.text.toString(), parking_text.text.toString(),
                price_text.text.toString())

            val randomInt: Int = Random.nextInt(0, Int.MAX_VALUE)
            database.child(Firebase.auth.currentUser!!.uid).child(randomInt.toString()).setValue(property).addOnSuccessListener {
                Toast.makeText(this , "updated in database" , Toast.LENGTH_SHORT).show()

                if (photoOne != null) {
                    storageRef.getReference(Firebase.auth.currentUser!!.uid).child(address_text.text.toString()+"image1")
                        .putFile(photoOne)
                        .addOnSuccessListener { task ->
                            task.metadata!!.reference!!.downloadUrl
                                .addOnSuccessListener {
                                    val userId = auth.currentUser!!.uid
                                    val mapImage = mapOf(
                                        "url" to it.toString()
                                    )
                                    val databaseReference = FirebaseDatabase.getInstance().getReference("UserImages")
                                    databaseReference.child(userId).setValue(mapImage)
                                        .addOnSuccessListener {
                                            Toast.makeText(this, "Succesfully Uploaded Image", Toast.LENGTH_SHORT).show()
                                        }
                                        .addOnFailureListener {error ->
                                            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                                        }
                                }
                        }
                }

            }

        }

    }
}