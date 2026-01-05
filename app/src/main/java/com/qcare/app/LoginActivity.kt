package com.qcare.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qcare.app.DashboardDokterActivity
import com.qcare.app.HomeActivity
import com.qcare.app.databinding.ActivityLoginBinding
import com.qcare.app.db.UserDatabaseHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    // In LoginActivity.kt
    private lateinit var db: UserDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UserDatabaseHelper(this)


        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()

            // VALIDASI INPUT
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Email dan password wajib diisi",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // CEK LOGIN
            val isLoginSuccess = db.login(email, password)

            if (isLoginSuccess) {

                val role = db.getUserRole(email)

                when (role) {
                    "dokter" -> {
                        startActivity(
                            Intent(this, DashboardDokterActivity::class.java)
                        )
                        finish()
                    }

                    "pasien" -> {
                        startActivity(
                            Intent(this, HomeActivity::class.java)
                        )
                        finish()
                    }

                    else -> {
                        Toast.makeText(
                            this,
                            "Role tidak valid",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                val session = SessionManager(this)

                if (isLoginSuccess) {
                    val role = db.getUserRole(email) ?: ""

                    session.saveLogin(email, role)

                    when (role) {
                        "dokter" -> startActivity(Intent(this, DashboardDokterActivity::class.java))
                        "pasien" -> startActivity(Intent(this, HomeActivity::class.java))
                        else -> {
                            Toast.makeText(this, "Role tidak valid", Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        }
                    }
                    finish()
                }


            } else {
                Toast.makeText(
                    this,
                    "Email atau password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // PINDAH KE REGISTER
        binding.tvToRegister.setOnClickListener {
            startActivity(
                Intent(this, RegisterActivity::class.java)
            )
        }
    }
}