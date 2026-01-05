package com.qcare.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qcare.app.databinding.ActivityFindDoctorBinding

class FindDoctorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        // ❌ TIDAK ADA btnAntri DI SINI
        // ❌ JANGAN PASANG LOGIC ANTRI DI ACTIVITY
    }
}