package com.example.qcare.util

import android.app.Activity
import android.content.Intent
import com.qcare.app.databinding.ViewBottomNavBinding
import com.qcare.app.HomeActivity
import com.qcare.app.KunjunganActivity
import com.qcare.app.ProfileActivity
import com.qcare.app.RiwayatActivity

object BottomNavHelper {

    fun setup(
        activity: Activity,
        binding: ViewBottomNavBinding,
        active: NavItem
    ) {

        resetState(binding)

        when (active) {
            NavItem.HOME -> binding.navHome.isSelected = true
            NavItem.KUNJUNGAN -> binding.navKunjungan.isSelected = true
            NavItem.RIWAYAT -> binding.navRiwayat.isSelected = true
            NavItem.PROFIL -> binding.navProfil.isSelected = true
        }

        binding.navHome.setOnClickListener {
            if (active != NavItem.HOME) {
                activity.startActivity(Intent(activity, HomeActivity::class.java))
                activity.finish()
            }
        }

        binding.navKunjungan.setOnClickListener {
            if (active != NavItem.KUNJUNGAN) {
                activity.startActivity(Intent(activity, KunjunganActivity::class.java))
                activity.finish()
            }
        }

        binding.navRiwayat.setOnClickListener {
            if (active != NavItem.RIWAYAT) {
                activity.startActivity(Intent(activity, RiwayatActivity::class.java))
                activity.finish()
            }
        }

        binding.navProfil.setOnClickListener {
            if (active != NavItem.PROFIL) {
                activity.startActivity(Intent(activity, ProfileActivity::class.java))
                activity.finish()
            }
        }
    }

    private fun resetState(binding: ViewBottomNavBinding) {
        binding.navHome.isSelected = false
        binding.navKunjungan.isSelected = false
        binding.navRiwayat.isSelected = false
        binding.navProfil.isSelected = false
    }
}

enum class NavItem {
    HOME,
    KUNJUNGAN,
    RIWAYAT,
    PROFIL
}
