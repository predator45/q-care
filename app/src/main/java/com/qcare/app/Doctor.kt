package com.qcare.app

data class Doctor(
    val id: Int,
    val name: String,
    val spec: String,
    var isReady: Boolean
)