package com.example.zeroraw
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.zeroraw.R

@Suppress("DEPRECATION")
class Listing : AppCompatActivity() {
    private lateinit var radio: RadioGroup
    private lateinit var bed: RadioGroup
    private lateinit var bal: RadioGroup
    private lateinit var bath: RadioGroup
    private lateinit var photo1: ImageView
    private lateinit var photo2: ImageView
    private val contact1 = registerForActivityResult(ActivityResultContracts.GetContent()){
        photo1.setImageURI(it)
    }
    private val contact2 = registerForActivityResult(ActivityResultContracts.GetContent()){
        photo2.setImageURI(it)
    }
    lateinit var next: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        radio = findViewById(R.id.Radio)
        bed = findViewById(R.id.beds)
        bal = findViewById(R.id.bals)
        bath = findViewById(R.id.baths)
        next = findViewById(R.id.next)
        val intent = Intent(this, Details::class.java)
        photo1 = findViewById(R.id.photo1)
        photo2 = findViewById(R.id.photo2)

        photo1.setOnClickListener {
            contact1.launch("image/*")
        }
        // Capture the drawing cache inside onActivityResult or wherever you handle the image selection result


        photo2.setOnClickListener {
            contact2.launch("image/*")

        }
        radio.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.apartment -> {
                    // Handle the "Apartment" radio button selection
                    Toast.makeText(this, "Apartment selected", Toast.LENGTH_SHORT).show()
                }
                R.id.villa -> {
                    // Handle the "Independent House/Villa" radio button selection
                    Toast.makeText(this, "Villa selected", Toast.LENGTH_SHORT).show()
                }
                R.id.floor -> {
                    // Handle the "Independent/Builder Floor" radio button selection
                    Toast.makeText(this, "Floor selected", Toast.LENGTH_SHORT).show()
                }
                R.id.studio -> {
                    // Handle the "1 Rk/Studio Apartment" radio button selection
                    Toast.makeText(this, "Studio selected", Toast.LENGTH_SHORT).show()
                }
                R.id.serviced -> {
                    // Handle the "Serviced Apartment" radio button selection
                    Toast.makeText(this, "Serviced selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
        bath.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.bath1 -> {
                    // Handle the "Apartment" radio button selection
                    Toast.makeText(this, "1 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bath2 -> {
                    // Handle the "Independent House/Villa" radio button selection
                    Toast.makeText(this, "2 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bath3 -> {
                    // Handle the "Independent/Builder Floor" radio button selection
                    Toast.makeText(this, "3 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bath4 -> {
                    // Handle the "1 Rk/Studio Apartment" radio button selection
                    Toast.makeText(this, "4 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bath5 -> {
                    // Handle the "Serviced Apartment" radio button selection
                    Toast.makeText(this, "5 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bath5p -> {
                    Toast.makeText(this, "5+ selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
        bal.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.bal1 -> {
                    // Handle the "Apartment" radio button selection
                    Toast.makeText(this, "1 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bal2 -> {
                    // Handle the "Independent House/Villa" radio button selection
                    Toast.makeText(this, "2 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bal3 -> {
                    // Handle the "Independent/Builder Floor" radio button selection
                    Toast.makeText(this, "3 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bal4 -> {
                    // Handle the "1 Rk/Studio Apartment" radio button selection
                    Toast.makeText(this, "4 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bal5 -> {
                    // Handle the "Serviced Apartment" radio button selection
                    Toast.makeText(this, "5 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bal5p -> {
                    Toast.makeText(this, "5+ selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

        bath.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.bath1 -> {
                    // Handle the "Apartment" radio button selection
                    Toast.makeText(this, "1 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bath2 -> {
                    // Handle the "Independent House/Villa" radio button selection
                    Toast.makeText(this, "2 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bath3 -> {
                    // Handle the "Independent/Builder Floor" radio button selection
                    Toast.makeText(this, "3 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bath4 -> {
                    // Handle the "1 Rk/Studio Apartment" radio button selection
                    Toast.makeText(this, "4 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bath5 -> {
                    // Handle the "Serviced Apartment" radio button selection
                    Toast.makeText(this, "5 selected", Toast.LENGTH_SHORT).show()
                }
                R.id.bath5p -> {
                    Toast.makeText(this, "5+ selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

//        photo2.isDrawingCacheEnabled = true
//        photo2.buildDrawingCache(true)
//        var bitmap2: Bitmap = photo1.drawingCache
//        if (bitmap2!= null){
//            intent.putExtra("photo2", bitmap2)
//        }
//        photo2.isDrawingCacheEnabled = false

        next.setOnClickListener {

            startActivity(intent)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
            // Handle the image selection here
            val selectedImageUri = data?.data

            // Check if selectedImageUri is not null and proceed to capture the drawing cache
            if (selectedImageUri != null) {
                // Set the selected image to the ImageView
                photo1.setImageURI(selectedImageUri)

                // Capture the drawing cache
                photo1.isDrawingCacheEnabled = true
                photo1.buildDrawingCache(true)
                val bitmap1: Bitmap? = photo1.drawingCache

                if (bitmap1 != null) {
                    // You have a valid Bitmap. You can use it here.
                    intent.putExtra("photo1", bitmap1)
                    Log.d("Photo1", "Photo converted to bitmap")
                }

                // Clean up the drawing cache
                photo1.isDrawingCacheEnabled = false
            }
        }
    }
}