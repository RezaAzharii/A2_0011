package com.example.finalprojectdaftarbuku.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllPenulisResponse(
    val status: Boolean,
    val message: String,
    val data: List<Penulis>
)

@Serializable
data class PenulisDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Penulis
)

@Serializable
data class Penulis(
    @SerialName("id_penulis")
    val idPenulis: Int,
    @SerialName("nama_penulis")
    val namaPenulis: String,
    val biografi: String,
    val kontak: String
)