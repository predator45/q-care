package com.example.qcare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qcare.databinding.ActivityRiwayatBinding
import com.example.qcare.databinding.ViewBottomNavBinding
import com.example.qcare.util.BottomNavHelper
import com.example.qcare.util.NavItem

class RiwayatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavBinding =
            ViewBottomNavBinding.bind(binding.bottomNav.root)

        BottomNavHelper.setup(
            this,
            bottomNavBinding,
            NavItem.RIWAYAT
        )
    }
}
