package com.example.qcare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qcare.databinding.ActivityHomeBinding
import com.example.qcare.databinding.ViewBottomNavBinding
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

        val bottomNavBinding =
            ViewBottomNavBinding.bind(binding.bottomNav.root)

        BottomNavHelper.setup(
            activity = this,
            binding = bottomNavBinding,
            active = NavItem.HOME
        )
    }
}
