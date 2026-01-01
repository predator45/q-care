package com.example.qcare

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qcare.databinding.ActivityKunjunganBinding
import com.example.qcare.databinding.ViewBottomNavBinding
import com.example.qcare.util.BottomNavHelper
import com.example.qcare.util.NavItem
import com.example.qcare.util.QueueState
import android.content.Intent


class KunjunganActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKunjunganBinding

    private fun showCancelDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_cencel_queue)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)

        dialog.findViewById<TextView>(R.id.btnNo).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<TextView>(R.id.btnYes).setOnClickListener {
            dialog.dismiss()


            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
            finish()
        }

        dialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKunjunganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bottom Nav
        val bottomNavBinding =
            ViewBottomNavBinding.bind(binding.bottomNav.root)

        BottomNavHelper.setup(
            this,
            bottomNavBinding,
            NavItem.KUNJUNGAN
        )

        // =========================
        // TOMBOL "SUDAH SAMPAI"
        // =========================
        binding.btnArrived.setOnClickListener {

            // 🔥 SET STATUS GLOBAL
            QueueState.sudahSampai = true

            // 🔔 NOTIFIKASI KE PASIEN
            Toast.makeText(
                this,
                "Anda baru saja sampai",
                Toast.LENGTH_SHORT
            ).show()

            // 🔒 OPTIONAL: NONAKTIFKAN BUTTON
            binding.btnArrived.isEnabled = false
            binding.btnArrived.alpha = 0.6f
            binding.btnArrived.text = "Sudah Sampai"
        }

        // CANCEL ANTRIAN
        binding.btnCancel.setOnClickListener {
            showCancelDialog()
        }
    }
}
