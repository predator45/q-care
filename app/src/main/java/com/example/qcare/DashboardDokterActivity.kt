package com.example.qcare

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashboardDokterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_dokter)

        // =========================
        // RECYCLER VIEW
        // =========================
        val rvDoctor = findViewById<RecyclerView>(R.id.rvDoctor)

        val doctors = mutableListOf(
            Doctor(1, "Dr. Suparma", "Dentis, City Dental", false),
            Doctor(2, "Dr. Rina", "Umum, QCare Clinic", true),
            Doctor(3, "Dr. Andi", "Anak, QCare Clinic", true)
        )

        rvDoctor.layoutManager = LinearLayoutManager(this)
        rvDoctor.adapter = DoctorAdapter(doctors)

        // =========================
        // BUTTON & FAB
        // =========================
        val btnAddDoctor = findViewById<MaterialButton>(R.id.btnAddDoctor)
        val fabNotification = findViewById<FloatingActionButton>(R.id.fabNotification)

        // Button → Tambah Dokter
        btnAddDoctor.setOnClickListener {
            startActivity(Intent(this, TambahDokterActivity::class.java))
        }

        // FAB → Kelola Antrian
        fabNotification.setOnClickListener {
            startActivity(Intent(this, KelolaAntrianActivity::class.java))
        }

        // =========================
        // BOTTOM NAV (CUSTOM)
        // =========================
        val navHome = findViewById<View>(R.id.navHome)
        val navTambahDokter = findViewById<View>(R.id.navTambahDokter)
        val navKelolaAntrian = findViewById<View>(R.id.navKelolaAntrian)

        // set ACTIVE menu (Dashboard = Home)
        navHome.isSelected = true

        navHome.setOnClickListener {
            // sudah di dashboard
        }

        navTambahDokter.setOnClickListener {
            startActivity(Intent(this, TambahDokterActivity::class.java))
        }

        navKelolaAntrian.setOnClickListener {
            startActivity(Intent(this, KelolaAntrianActivity::class.java))
        }
    }
}
