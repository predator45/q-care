package com.qcare.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qcare.app.LoginActivity
import com.qcare.app.R
import com.qcare.app.databinding.ActivityRegisterBinding
import com.qcare.app.db.UserDatabaseHelper

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UserDatabaseHelper(this)

        // ================= REGISTER =================
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val confirm = binding.etConfirmPassword.text.toString()

            val role = when (binding.rgRole.checkedRadioButtonId) {
                R.id.rbPasien -> "pasien"
                R.id.rbDokter -> "dokter"
                else -> ""
            }

            if (email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Semua field wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (role.isEmpty()) {
                Toast.makeText(this, "Silakan pilih role", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirm) {
                Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val success = db.register(email, password, role)
            if (success) {
                Toast.makeText(this, "Register berhasil", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Email sudah terdaftar", Toast.LENGTH_SHORT).show()
            }
        }

        // ================= PINDAH KE LOGIN =================
        binding.tvToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // ⬅️ PENTING supaya tidak balik ke Register
        }
    }
}