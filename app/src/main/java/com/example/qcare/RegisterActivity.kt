package com.example.qcare

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qcare.databinding.ActivityRegisterBinding
import com.example.qcare.db.UserDatabaseHelper

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    // 1. Declare the 'db' variable here
    private lateinit var db: UserDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Initialize the 'db' variable
        db = UserDatabaseHelper(this)

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val confirm = binding.etConfirmPassword.text.toString()

            // AMBIL ROLE
            val role = when (binding.rgRole.checkedRadioButtonId) {
                R.id.rbPasien -> "pasien"
                R.id.rbDokter -> "dokter"
                else -> ""
            }

            // VALIDASI
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

            // SIMPAN KE DATABASE (ROLE IKUT)
            // 3. Now 'db' can be accessed without error
            val success = db.register(email, password, role)

            if (success) {
                Toast.makeText(this, "Register berhasil sebagai $role", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Email sudah terdaftar", Toast.LENGTH_SHORT).show()
            }

        }
    }

}
