package com.example.qcare

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qcare.databinding.ActivityFindDoctorBinding
import com.example.qcare.util.RequestQueueState

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
