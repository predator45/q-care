package com.example.qcare

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qcare.util.QueueState

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
                sudahSampai = QueueState.sudahSampai,
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
        // BOTTOM NAV (CUSTOM)
        // =====================
        val navHome = findViewById<View>(R.id.navHome)
        val navTambahDokter = findViewById<View>(R.id.navTambahDokter)
        val navKelolaAntrian = findViewById<View>(R.id.navKelolaAntrian)

        // set ACTIVE menu (Kelola Antrian)
        navKelolaAntrian.isSelected = true

        navHome.setOnClickListener {
            startActivity(Intent(this, DashboardDokterActivity::class.java))
            finish()
        }

        navTambahDokter.setOnClickListener {
            startActivity(Intent(this, TambahDokterActivity::class.java))
            finish()
        }

        navKelolaAntrian.setOnClickListener {
            // sudah di halaman ini
        }

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
