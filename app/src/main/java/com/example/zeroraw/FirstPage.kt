package com.example.zeroraw


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class FirstPage : AppCompatActivity() {
    lateinit var residential: CardView
    lateinit var prev: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)
        residential = findViewById(R.id.residential)
        prev = findViewById(R.id.previous)
        residential.setOnClickListener {
            val intent = Intent(this, Listing::class.java)
            intent.putExtra("type", "Residential")
            startActivity(intent)
        }
        prev.setOnClickListener {
            val intent = Intent(this, Lists::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {

    }
}