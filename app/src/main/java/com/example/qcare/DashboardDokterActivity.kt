package com.example.qcare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardDokterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_dokter)

        // =========================
        // RECYCLER VIEW (LIST DOKTER)
        // =========================
        val rvDoctor = findViewById<RecyclerView>(R.id.rvDoctor)

        val doctors = listOf(
            Doctor(1, "Dr. Suparma", "Dentis, City Dental", false),
            Doctor(2, "Dr. Rina", "Umum, QCare Clinic", true),
            Doctor(3, "Dr. Andi", "Anak, QCare Clinic", true)
        )

        rvDoctor.layoutManager = LinearLayoutManager(this)
        rvDoctor.adapter = DoctorAdapter(doctors)

        // =========================
        // BOTTOM NAVIGATION LOGIC
        // =========================
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // tandai menu dashboard sebagai aktif
        bottomNav.selectedItemId = R.id.nav_dashboard

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_dashboard -> {
                    // sudah di dashboard
                    true
                }

                R.id.nav_add_doctor -> {
                    startActivity(
                        Intent(this, TambahDokterActivity::class.java)
                    )
                    true
                }

                R.id.nav_manage -> {
                    startActivity(
                        Intent(this, KelolaAntrianActivity::class.java)
                    )
                    true
                }

                else -> false
            }
        }
    }
}
