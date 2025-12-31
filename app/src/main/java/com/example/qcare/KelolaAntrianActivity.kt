package com.example.qcare

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qcare.util.QueueState
import com.google.android.material.bottomnavigation.BottomNavigationView

class KelolaAntrianActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelola_antrian)

        // =====================
        // DATA ANTRIAN
        // =====================
        val antrianList = mutableListOf(
            Antrian(
                nomor = "B-01",
                estimasiMenit = 0,
                sudahSampai = QueueState.sudahSampai, // 🔥 TERHUBUNG DARI PASIEN
                sedangDipanggil = false,
                selesai = false
            ),
            Antrian("B-02", 5, true, false, false),
            Antrian("B-03", 45, true, false, false)
        )

        // =====================
        // DATA REQUEST PASIEN
        // =====================
        val requestList = mutableListOf(
            RequestAntrian("Muhammad Bobi", "Dr. Suparma", "B", RequestStatus.PENDING),
            RequestAntrian("Ahmad Nurdin", "Dr. Suparma", "B", RequestStatus.PENDING)
        )

        // =====================
        // RV ANTRIAN
        // =====================
        findViewById<RecyclerView>(R.id.rvAntrian).apply {
            layoutManager = LinearLayoutManager(this@KelolaAntrianActivity)
            adapter = AntrianAdapter(antrianList)
        }

        // =====================
        // RV REQUEST
        // =====================
        findViewById<RecyclerView>(R.id.rvRequest).apply {
            layoutManager = LinearLayoutManager(this@KelolaAntrianActivity)
            adapter = RequestAdapter(requestList)
        }

        // =====================
        // NAVBAR
        // =====================
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_manage

        // =====================
        // NOTIF JIKA PASIEN SUDAH SAMPAI
        // =====================
        if (QueueState.sudahSampai) {
            Toast.makeText(
                this,
                "Pasien sudah sampai",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
