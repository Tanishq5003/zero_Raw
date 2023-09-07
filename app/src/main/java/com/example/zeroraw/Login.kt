package com.example.zeroraw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    lateinit var mail: EditText
    lateinit var pass: EditText
    lateinit var login:Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mail = findViewById(R.id.mail)
        pass = findViewById(R.id.pass)
        login = findViewById(R.id.login)
        auth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            var text_mail = mail.text.toString()
            var text_pass = pass.text.toString()
            login_user(text_mail, text_pass)
        }

    }

    private fun login_user(email: String, pass: String){
        auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(this) { task ->
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, FirstPage::class.java)
            startActivity(intent)
            finish()
        }
    }

}