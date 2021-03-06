package com.example.proyectomarcos.principal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.proyectomarcos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    //Declaramos variables
    private lateinit var registerEmail: EditText
    private lateinit var registerPassword: EditText
    private lateinit var registerPasswordAgain: EditText
    private lateinit var registerNombre: EditText
    private lateinit var registerApellido: EditText
    private lateinit var registerTelefono: EditText
    private lateinit var registerButton: Button
    private lateinit var registerLogin: Button
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("users")

        //Conectamos con su XML
        registerEmail = findViewById(R.id.registerEmail)
        registerPassword = findViewById(R.id.registerPassword)
        registerNombre = findViewById(R.id.registerNombre)
        registerApellido = findViewById(R.id.registerApellido)
        registerTelefono = findViewById(R.id.registerTelefono)
        registerButton = findViewById(R.id.registerButton)
        registerLogin = findViewById(R.id.registerLogin)

        //con el RegisterButton cogemos los valores de todos los campos y llamamos a la
        //    función register

        registerButton.setOnClickListener {
            val email = registerEmail.text.toString()
            val pass = registerPassword.text.toString()
            val nombre = registerNombre.text.toString()
            val apellido = registerApellido.text.toString()
            val telefono = registerTelefono.text.toString()


            if (checkLength(pass)) {
                register(email, pass, nombre, apellido, telefono)
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.invalid_password),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // al pulsar el boton de login volvemos a la primera Activity
        registerLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivityKT::class.java))
            finish()
        }
    }

//    Función llamada al pulsar el botón registro dónde pasamos el valor de todos los campos
//    ejecutamos el metodo de Firebase "createUserWithEmailAndPassword" y si todo es correcto
//    se crea un mapa para añadirlo a la coleccion y guardarlo en la BD

    private fun register(email: String, pass: String, nombre: String, apellido: String,
        telefono: String) {
        val db = Firebase.firestore

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user: FirebaseUser? = auth.currentUser
                val dbUser = hashMapOf(
                    "uid" to user!!.uid, "correo" to email, "nombre" to nombre,
                    "apellido" to apellido, "telefono" to telefono, "esJunta" to false
                )

                db.collection("usuarios").document(user.uid).set(dbUser)
                    .addOnSuccessListener {
                        Toast.makeText(
                            applicationContext, "Usuario creado",
                            Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            applicationContext, "Register failed", Toast.LENGTH_SHORT
                        ).show()
                    }

                var intent = Intent(this, DrawerActivity::class.java)
                intent.putExtra("email", email);
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    applicationContext, getString(R.string.invalid_username),
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkLength(pass: String): Boolean {
        return pass.length >= 6
    }

    private fun checkEmpty(email: String, pass: String, passAgain: String): Boolean {
        return email.isNotEmpty() && pass.isNotEmpty() && passAgain.isNotEmpty()
    }
}