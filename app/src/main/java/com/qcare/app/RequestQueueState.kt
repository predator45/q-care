package com.qcare.app

object RequestQueueState {

    val requestList: MutableList<RequestAntrian> = mutableListOf()

    fun addRequest(
        namaPasien: String,
        dokter: String,
        kode: String
    ) {
        requestList.add(
            RequestAntrian(
                namaPasien = namaPasien,
                dokter = dokter,
                kode = kode,
                status = RequestStatus.PENDING
            )
        )
    }
}