package com.example.zeroraw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast

class Listing : AppCompatActivity() {
    private lateinit var radio: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        radio = findViewById(R.id.Radio)
//        radio.setOnCheckedChangeListener { group, checkedId ->
//            when(checkedId){
//                R.id.apartment -> {
//                    // Handle the "Apartment" radio button selection
//                    Toast.makeText(this, "Apartment selected", Toast.LENGTH_SHORT).show()
//                }
//                R.id.villa -> {
//                    // Handle the "Independent House/Villa" radio button selection
//                    Toast.makeText(this, "Villa selected", Toast.LENGTH_SHORT).show()
//                }
//                R.id.floor -> {
//                    // Handle the "Independent/Builder Floor" radio button selection
//                    Toast.makeText(this, "Floor selected", Toast.LENGTH_SHORT).show()
//                }
//                R.id.studio -> {
//                    // Handle the "1 Rk/Studio Apartment" radio button selection
//                    Toast.makeText(this, "Studio selected", Toast.LENGTH_SHORT).show()
//                }
//                R.id.serviced -> {
//                    // Handle the "Serviced Apartment" radio button selection
//                    Toast.makeText(this, "Serviced selected", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

    }
}