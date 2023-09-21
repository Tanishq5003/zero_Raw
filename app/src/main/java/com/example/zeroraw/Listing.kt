package com.example.zeroraw
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.zeroraw.R

@Suppress("DEPRECATION")
class Listing : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST_CODE = 1
    private var imageUri1: Uri? = null
    private var imageUri2: Uri? = null
    private lateinit var radio: RadioGroup
    private lateinit var bed: RadioGroup
    private lateinit var bal: RadioGroup
    private lateinit var bath: RadioGroup
    private lateinit var photo1: ImageView
    private lateinit var photo2: ImageView
    lateinit var address: EditText
    lateinit var price: EditText
    lateinit var propertyType: String
    lateinit var nbed: String
    lateinit var nbath: String
    lateinit var nbal: String

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
        address = findViewById(R.id.address)
        price = findViewById(R.id.price)


        photo1.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
        }
        // Capture the drawing cache inside onActivityResult or wherever you handle the image selection result


        photo2.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
        }
        radio.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.apartment -> {
                    // Handle the "Apartment" radio button selection
                    Toast.makeText(this, "Apartment selected", Toast.LENGTH_SHORT).show()
                    propertyType = "Apartment"
                }
                R.id.villa -> {
                    // Handle the "Independent House/Villa" radio button selection
                    Toast.makeText(this, "Villa selected", Toast.LENGTH_SHORT).show()
                    propertyType = "Villa"
                }
                R.id.floor -> {
                    // Handle the "Independent/Builder Floor" radio button selection
                    Toast.makeText(this, "Floor selected", Toast.LENGTH_SHORT).show()
                    propertyType = "Independent/ Builder Floor"
                }
                R.id.studio -> {
                    // Handle the "1 Rk/Studio Apartment" radio button selection
                    Toast.makeText(this, "Studio selected", Toast.LENGTH_SHORT).show()
                    propertyType = "1 RK/Studio Apartment"
                }
                R.id.serviced -> {
                    // Handle the "Serviced Apartment" radio button selection
                    Toast.makeText(this, "Serviced selected", Toast.LENGTH_SHORT).show()
                    propertyType = "Serviced Apartment"
                }
            }
        }
        bath.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.bath1 -> {
                    // Handle the "Apartment" radio button selection
                    Toast.makeText(this, "1 selected", Toast.LENGTH_SHORT).show()
                    nbath = "1"
                }
                R.id.bath2 -> {
                    // Handle the "Independent House/Villa" radio button selection
                    Toast.makeText(this, "2 selected", Toast.LENGTH_SHORT).show()
                    nbath = "2"
                }
                R.id.bath3 -> {
                    // Handle the "Independent/Builder Floor" radio button selection
                    Toast.makeText(this, "3 selected", Toast.LENGTH_SHORT).show()
                    nbath = "3"
                }
                R.id.bath4 -> {
                    // Handle the "1 Rk/Studio Apartment" radio button selection
                    Toast.makeText(this, "4 selected", Toast.LENGTH_SHORT).show()
                    nbath = "4"
                }
                R.id.bath5 -> {
                    // Handle the "Serviced Apartment" radio button selection
                    Toast.makeText(this, "5 selected", Toast.LENGTH_SHORT).show()
                    nbath = "5"
                }
                R.id.bath5p -> {
                    Toast.makeText(this, "5+ selected", Toast.LENGTH_SHORT).show()
                    nbath = "5+"
                }
            }
        }
        bal.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.bal1 -> {
                    // Handle the "Apartment" radio button selection
                    Toast.makeText(this, "1 selected", Toast.LENGTH_SHORT).show()
                    nbal = "1"
                }
                R.id.bal2 -> {
                    // Handle the "Independent House/Villa" radio button selection
                    Toast.makeText(this, "2 selected", Toast.LENGTH_SHORT).show()
                    nbal = "2"
                }
                R.id.bal3 -> {
                    // Handle the "Independent/Builder Floor" radio button selection
                    Toast.makeText(this, "3 selected", Toast.LENGTH_SHORT).show()
                    nbal = "3"
                }
                R.id.bal4 -> {
                    // Handle the "1 Rk/Studio Apartment" radio button selection
                    Toast.makeText(this, "4 selected", Toast.LENGTH_SHORT).show()
                    nbal = "4"
                }
                R.id.bal5 -> {
                    // Handle the "Serviced Apartment" radio button selection
                    Toast.makeText(this, "5 selected", Toast.LENGTH_SHORT).show()
                    nbal = "5"
                }
                R.id.bal5p -> {
                    Toast.makeText(this, "5+ selected", Toast.LENGTH_SHORT).show()
                    nbal = "5+"
                }
            }
        }

        bed.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.bed1 -> {
                    // Handle the "Apartment" radio button selection
                    Toast.makeText(this, "1 selected", Toast.LENGTH_SHORT).show()
                    nbed = "1"
                }
                R.id.bed2 -> {
                    // Handle the "Independent House/Villa" radio button selection
                    Toast.makeText(this, "2 selected", Toast.LENGTH_SHORT).show()
                    nbed = "2"
                }
                R.id.bed3 -> {
                    // Handle the "Independent/Builder Floor" radio button selection
                    Toast.makeText(this, "3 selected", Toast.LENGTH_SHORT).show()
                    nbed = "3"
                }
                R.id.bed4 -> {
                    // Handle the "1 Rk/Studio Apartment" radio button selection
                    Toast.makeText(this, "4 selected", Toast.LENGTH_SHORT).show()
                    nbed = "4"
                }
                R.id.bed5 -> {
                    // Handle the "Serviced Apartment" radio button selection
                    Toast.makeText(this, "5 selected", Toast.LENGTH_SHORT).show()
                    nbed = "5"
                }
                R.id.bed5p -> {
                    Toast.makeText(this, "5+ selected", Toast.LENGTH_SHORT).show()
                    nbed = "5+"
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
            var address_text: String = address.text.toString()
            var price_text: String = price.text.toString()
            intent.putExtra("Address", address_text)
            intent.putExtra("Price", price_text)
            intent.putExtra("Property type", propertyType)
            intent.putExtra("nbed", nbed)
            intent.putExtra("nbath", nbath)
            intent.putExtra("nbal", nbal)
            intent.putExtra("IMAGE_URI_1", imageUri1)
            intent.putExtra("IMAGE_URI_2", imageUri2)
            startActivity(intent)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            // Get the image URI from the Intent.
            val imageUri = data?.data

            // Set the image URI for the ImageView.
            if (imageUri1 == null) {
                imageUri1 = imageUri
                photo1.setImageURI(imageUri1)
            } else {

                imageUri2 = imageUri
                photo2.setImageURI(imageUri2)
            }
        }
    }
}