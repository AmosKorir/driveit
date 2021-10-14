package com.bookit.koriramos.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bookit.koriramos.R
import com.bookit.koriramos.viewmodels.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
	private val mainViewModel: MainViewModel by viewModel()
	private lateinit var mAuth: FirebaseAuth
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)
		mAuth = FirebaseAuth.getInstance()
		
		findViewById<Button>(R.id.loginBtn).setOnClickListener {
			loginUser()
		}
		findViewById<Button>(R.id.fireBaseLoginBtn).setOnClickListener {
			loginFirebase()
		}
		
		with(mainViewModel) {
			handleError.observe(this@LoginActivity, {
				it.message?.let { it1 -> showToast(it1) }
			})
			
			loginSuccess.observe(this@LoginActivity, {
				startActivity(Intent(this@LoginActivity, MainActivity::class.java))
				finish()
			})
		}
	}
	
	private fun showToast(message: String) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
	}
	
	private fun CharSequence?.isValidEmail() =
		!isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
	
	private fun loginFirebase() {
		val userName = findViewById<EditText>(R.id.editTextTextPersonName).text.toString().trim()
		val password = findViewById<EditText>(R.id.editTextTextPassword).text.toString().trim()
		
		if (userName.isNotBlank() && password.isNotBlank()) {
			if (userName.isValidEmail()) {
				mAuth.signInWithEmailAndPassword(userName, password).
					addOnSuccessListener {
						startActivity(Intent(this@LoginActivity, MainActivity::class.java))
						finish()
					}
					.addOnFailureListener{
						it.printStackTrace()
						showToast("Please check on your credentials")
					}
			} else {
				showToast("Please enter a valid email")
			}
			
		} else {
			showToast("Please enter all field")
		}
	}
	
	private fun loginUser() {
		val userName = findViewById<EditText>(R.id.editTextTextPersonName).text.toString().trim()
		val password = findViewById<EditText>(R.id.editTextTextPassword).text.toString().trim()
		
		if (userName.isNotBlank() && password.isNotBlank()) {
			if (userName.isValidEmail()) {
				mainViewModel.loginUser(userName, password)
			} else {
				Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
			}
			
		} else {
			Toast.makeText(this, "Please enter all field", Toast.LENGTH_SHORT).show()
		}
	}
}