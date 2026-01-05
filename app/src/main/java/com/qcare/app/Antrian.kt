package com.qcare.app

data class Antrian(
    val nomor: String,
    var estimasiMenit: Int,
    var sudahSampai: Boolean,
    var sedangDipanggil: Boolean,
    var selesai: Boolean
)