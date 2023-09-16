package com.example.zeroraw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup

class Details : AppCompatActivity() {
    lateinit var basement_text: RadioGroup
    lateinit var basement: String
    lateinit var totalArea: EditText
    lateinit var totalArea_text:String
    lateinit var buildupArea: EditText
    lateinit var buildupArea_text:String
    lateinit var carpetArea: EditText
    lateinit var carpetArea_text:String
    lateinit var totalFloors: EditText
    lateinit var totalFloors_text:String
    lateinit var pooja: CheckBox
    lateinit var servant: CheckBox
    lateinit var study: CheckBox
    lateinit var other: CheckBox
    lateinit var furnishingStatus: RadioGroup
    lateinit var furnished: String
    lateinit var parkingStatus: RadioGroup
    lateinit var parking: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }
}