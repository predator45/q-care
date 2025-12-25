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

class KunjunganActivity : AppCompatActivity() {

    private fun showCancelDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_cencel_queue)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)

        val btnYes = dialog.findViewById<TextView>(R.id.btnYes)
        val btnNo = dialog.findViewById<TextView>(R.id.btnNo)

        btnNo.setOnClickListener {
            dialog.dismiss()
        }

        btnYes.setOnClickListener {
            dialog.dismiss()
            finish()
        }

        dialog.show()
    }


    private lateinit var binding: ActivityKunjunganBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKunjunganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavBinding =
            ViewBottomNavBinding.bind(binding.bottomNav.root)

        BottomNavHelper.setup(
            this,
            bottomNavBinding,
            NavItem.KUNJUNGAN
        )

        binding.btnArrived.setOnClickListener {
            // aksi sudah sampai
            Toast.makeText(this, "Sudah Sampai", Toast.LENGTH_SHORT).show()
        }

        binding.btnCancel.setOnClickListener {
            showCancelDialog()
        }

    }
}

