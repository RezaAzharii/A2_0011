package com.example.finalprojectdaftarbuku.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllPenerbitResponse(
    val status: Boolean,
    val message: String,
    val data: List<Penerbit>
)

@Serializable
data class PenerbitDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Penerbit
)

@Serializable
data class Penerbit(
    @SerialName("id_penerbit")
    val idPenerbit: Int,
    @SerialName("nama_penerbit")
    val namaPenerbit: String,
    @SerialName("alamat_penerbit")
    val alamatPenerbit: String,
    @SerialName("telepon_penerbit")
    val telpPenerbit: String
)