package com.example.qcare

data class RequestAntrian(
    val namaPasien: String,
    val dokter: String,
    val kode: String,
    var status: RequestStatus
)

enum class RequestStatus {
    PENDING, DITERIMA, DITOLAK
}
