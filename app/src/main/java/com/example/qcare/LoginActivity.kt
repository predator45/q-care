package com.example.qcare

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qcare.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
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

                // 🔥 AMBIL ROLE USER
                val role = db.getUserRole(email)

                if (role == "dokter") {
                    // 👉 KE DASHBOARD DOKTER
                    startActivity(
                        Intent(this, DashboardDokterActivity::class.java)
                    )
                } else if (role == "pasien") {
                    // 👉 KE DASHBOARD PASIEN
                    startActivity(
                        Intent(this, HomeActivity::class.java)
                    )
                } else {
                    // JAGA-JAGA KALAU ROLE NULL / SALAH
                    Toast.makeText(
                        this,
                        "Role tidak valid",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                finish()

            } else {
                // LOGIN GAGAL
                Toast.makeText(
                    this,
                    "Email atau password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.tvToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
