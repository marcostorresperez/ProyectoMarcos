package com.example.proyectomarcos.principal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectomarcos.R
import com.example.proyectomarcos.pojo.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class LoginActivityKT : AppCompatActivity() {

    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var loginRegister: Button

    private lateinit var auth: FirebaseAuth

    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginkt)

        database =
            FirebaseDatabase.getInstance("https://watchdeviationchecker-default-rtdb.europe-west1.firebasedatabase.app/")
        myRef = database.getReference("users")

        auth = Firebase.auth

        loginEmail = findViewById(R.id.edUser)
        loginPassword = findViewById(R.id.edPass)
        loginButton = findViewById(R.id.btnLogin)
        loginRegister = findViewById(R.id.btnRegister)

        loginButton.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()
            if (password.isNotEmpty() && email.isNotEmpty()) {
                login(email, password)
            }
        }

        loginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var intent = Intent(this, PrincipalActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.login_failed),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}