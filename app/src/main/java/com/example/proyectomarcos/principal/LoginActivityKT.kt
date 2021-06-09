package com.example.proyectomarcos.principal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectomarcos.R
import com.example.proyectomarcos.pojo.Usuario
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class LoginActivityKT : AppCompatActivity() {
    private val GOOGLE_SIGN_IN = 100

    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var loginRegister: Button
    private lateinit var loginGoogle: ImageButton

    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginkt)

        auth = Firebase.auth

        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("users")

        loginEmail = findViewById(R.id.edUser)
        loginPassword = findViewById(R.id.edPass)
        loginButton = findViewById(R.id.btnLogin)
        loginRegister = findViewById(R.id.btnRegister)
        loginGoogle = findViewById(R.id.btnGLogin)

        session()

//Implementamos los listeners de los 3 botones que llamaran a sus metodos correspondientes
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

        loginGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }
    }

    //Login mediante Google Sign In
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential: AuthCredential =
                        GoogleAuthProvider.getCredential(account.idToken, null)

                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {

//Si todo es correcto creamos un usuario nuevo en nuestro Firebase, si es la primera vez que
//conectamos con esta cuenta nos llevará al registro para terminar de completarlos, sino, iniciará sesión directamente
                            if (it.isSuccessful) {
                                val user: FirebaseUser = it.result!!.user!!
                                val db: FirebaseFirestore = FirebaseFirestore.getInstance();
                                db.collection("usuarios").document(user.uid).get()
                                    .addOnCompleteListener(
                                        OnCompleteListener {
                                            if (it.result!!.exists()) {
                                                entrar()
                                            } else {
                                                entrarGoogle()
                                            }
                                        }).addOnFailureListener(OnFailureListener {
                                        entrarGoogle();
                                    })
                            } else {
                            }
                        }
                }
            } catch (e: ApiException) {
            }
        }
    }

    private fun entrarGoogle() {
        val intent: Intent = Intent(this, RegisterGoogleActivity::class.java)
        startActivity(intent)
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var intent = Intent(this, DrawerActivity::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                    finish()
                } else {
                }
            }
    }

    private fun session() {
        val prefs = getSharedPreferences(
            getString(R.string.prefs_file),
            MODE_PRIVATE
        )
        val email = prefs.getString("email", null)

        if (email != null) {
            login(email, loginPassword.text.toString());
        }
    }

    private fun entrar() {
        val intent: Intent = Intent(this, DrawerActivity::class.java)
        startActivity(intent)
    }


}
