package com.qcare.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qcare.app.FindDoctorActivity
import com.qcare.app.databinding.ActivityHomeBinding
import com.qcare.app.databinding.ViewBottomNavBinding
import com.example.qcare.util.BottomNavHelper
import com.example.qcare.util.NavItem

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardFindDoctor.setOnClickListener {
            startActivity(Intent(this, FindDoctorActivity::class.java))
        }

        binding.cardHistory.setOnClickListener {
            startActivity(Intent(this, RiwayatActivity::class.java))
        }

        binding.cardAntrianBerlangsung.setOnClickListener {
            startActivity(Intent(this, KunjunganActivity::class.java))
        }


        val bottomNavBinding =
            ViewBottomNavBinding.bind(binding.bottomNav.root)

        BottomNavHelper.setup(
            activity = this,
            binding = bottomNavBinding,
            active = NavItem.HOME
        )
    }
}