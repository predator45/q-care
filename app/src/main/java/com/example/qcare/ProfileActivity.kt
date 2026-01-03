package com.example.qcare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qcare.databinding.ActivityProfileBinding
import com.example.qcare.databinding.ViewBottomNavBinding
import com.example.qcare.util.BottomNavHelper
import com.example.qcare.util.NavItem
import android.content.Intent
import com.example.qcare.db.UserDatabaseHelper
import com.example.qcare.util.SessionManager

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGantiPassword.setOnClickListener {
            startActivity(
                Intent(this, PrivacyActivity::class.java)
            )
        }

        val bottomNavBinding =
            ViewBottomNavBinding.bind(binding.bottomNav.root)

        BottomNavHelper.setup(
            this,
            bottomNavBinding,
            NavItem.PROFIL
        )


        val session = SessionManager(this)

// JIKA BELUM LOGIN → BALIK KE LOGIN
        if (!session.isLogin()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

// AMBIL EMAIL USER LOGIN
        val email = session.getEmail()!!

// AMBIL DATA USER DARI SQLITE
        val db = UserDatabaseHelper(this)
        val cursor = db.getUserByEmail(email)

        if (cursor.moveToFirst()) {
            binding.tvEmail.text = email
            binding.tvRole.text = cursor.getString(
                cursor.getColumnIndexOrThrow("role")
            )
        }

        cursor.close()


    }


}
