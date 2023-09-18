package com.example.zeroraw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    lateinit var otherRooms: ArrayList<String>
    lateinit var intent1  :Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        basement_text = findViewById(R.id.basement_text)
        basement_text.setOnCheckedChangeListener{ group , checkedId ->
            when(checkedId){
                R.id.basement_yes ->{
                    basement = "Yes"
                }
                R.id.basement_no ->{
                    basement = "No"
                }
            }
        }
        furnishingStatus = findViewById(R.id.furnishing)
        furnishingStatus.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.furnished ->{
                    furnished = "Yes"
                }
                R.id.not_furnished ->{
                    furnished = "No"
                }
            }
        }
        parkingStatus = findViewById(R.id.parking)
        parkingStatus.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.parking_yes -> {
                    parking = "Yes"
                }
                R.id.parking_no ->{
                    parking = "No"
                }
            }
        }
        totalArea = findViewById(R.id.area_total)
        carpetArea = findViewById(R.id.area_carpet)
        buildupArea = findViewById(R.id.area_buildup)
        totalFloors = findViewById(R.id.floors)
        pooja = findViewById(R.id.pooja)
        servant = findViewById(R.id.servant)
        study = findViewById(R.id.study)
        other = findViewById(R.id.other)
        pooja.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
            otherRooms.add("Pooja Room")
                Log.d("Room Added", "Pooja Room")
            }
            else{
                otherRooms.removeIf { it == "Pooja Room" }
                Log.d("Room Removed", "Pooja Room")
                }
            }
        servant.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
            otherRooms.add("Servant Room")
            }
            else{
                otherRooms.removeIf { it == "Servant Room" }
                }
            }
        study.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
            otherRooms.add("Study Room")
            }
            else{
                otherRooms.removeIf { it == "Study Room" }
                }
            }
        other.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
            otherRooms.add("Other Rooms")
            }
            else{
                otherRooms.removeIf { it == "Other Room" }
                }
            }
        val address = intent.getStringExtra("Address")
        val price = intent.getStringExtra("Price")
        val type = intent.getStringExtra("Property type")
        val nbed = intent.getStringExtra("nbed")
        val nbal = intent.getStringExtra("nbal")
        val nbath = intent.getStringExtra("nbath")
        val photo1 = intent.getIntExtra("photo1", 0)
        val photo2 = intent.getIntExtra("photo2", 0)
        }
    }
