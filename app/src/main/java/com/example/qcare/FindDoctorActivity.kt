package com.example.qcare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qcare.databinding.ActivityFindDoctorBinding

class FindDoctorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
