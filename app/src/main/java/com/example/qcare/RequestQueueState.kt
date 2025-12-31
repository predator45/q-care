package com.example.qcare.util

import com.example.qcare.RequestAntrian
import com.example.qcare.RequestStatus

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
