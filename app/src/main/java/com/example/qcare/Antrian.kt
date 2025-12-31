package com.example.qcare

data class Antrian(
    val nomor: String,
    var estimasiMenit: Int,
    var sudahSampai: Boolean,
    var sedangDipanggil: Boolean,
    var selesai: Boolean
)
