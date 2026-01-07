package com.qcare.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qcare.app.databinding.ActivityLoginBinding
import com.qcare.app.db.UserDatabaseHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: UserDatabaseHelper
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UserDatabaseHelper(this)
        session = SessionManager(this)

        // 🔐 LOGIN
        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Email dan password wajib diisi",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // CEK LOGIN
            if (db.login(email, password)) {

                val role = db.getUserRole(email)

                if (role == null) {
                    Toast.makeText(this, "Role tidak valid", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // SIMPAN SESSION
                session.saveLogin(email, role)

                // ROUTING BERDASARKAN ROLE
                when (role) {
                    "dokter" -> {
                        startActivity(
                            Intent(this, DashboardDokterActivity::class.java)
                        )
                    }
                    "pasien" -> {
                        startActivity(
                            Intent(this, HomeActivity::class.java)
                        )
                    }
                    else -> {
                        Toast.makeText(
                            this,
                            "Role tidak dikenali",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }
                }

                // TUTUP LOGIN
                finish()

            } else {
                Toast.makeText(
                    this,
                    "Email atau password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // 👉 KE REGISTER
        binding.tvToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
