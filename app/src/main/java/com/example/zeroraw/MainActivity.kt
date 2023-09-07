package com.example.zeroraw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DatabaseRegistrar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var name:EditText
    private lateinit var mail: EditText
    private lateinit var pass: EditText
    private lateinit var register: Button
    private lateinit var login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.name)
        mail = findViewById(R.id.mail)
        pass = findViewById(R.id.pass)
        register = findViewById(R.id.Register)
        login = findViewById(R.id.login)
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference


        register.setOnClickListener {
            var txt_email: String = mail.text.toString()
            var txt_password: String = pass.text.toString()

            if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                Toast.makeText(this, "Empty Credential", Toast.LENGTH_SHORT).show()
            } else if (txt_password.length < 6) {
                Toast.makeText(
                    this,
                    "Password should contain at least 6 characters",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                registerUser(txt_email, txt_password)
            }
        }
        login.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }


    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful()) {
                var txt_name: String = name.text.toString()

                var email: String = mail.text.toString()
                Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show()

                database.child("First Name").child(Firebase.auth.currentUser!!.uid)
                    .child(Firebase.auth.currentUser!!.uid).setValue(txt_name)

                database.child("Email").child(Firebase.auth.currentUser!!.uid)
                    .child(Firebase.auth.currentUser!!.uid).setValue(email)
                var intent = Intent(this , FirstPage::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this , "User Registration Failed", Toast.LENGTH_SHORT).show()
            }
            }
        }
    }
