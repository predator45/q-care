package com.example.qcare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qcare.databinding.ActivityProfileBinding
import com.example.qcare.databinding.ViewBottomNavBinding
import com.example.qcare.util.BottomNavHelper
import com.example.qcare.util.NavItem

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavBinding =
            ViewBottomNavBinding.bind(binding.bottomNav.root)

        BottomNavHelper.setup(
            this,
            bottomNavBinding,
            NavItem.PROFIL
        )
    }
}
