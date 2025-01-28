package com.example.finalprojectdaftarbuku.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ALlKategoriResponse(
    val status: Boolean,
    val message: String,
    val data: List<Kategori>
)

@Serializable
data class KategoriDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Kategori
)

@Serializable
data class Kategori(
    @SerialName("id_kategori")
    val idKategori: Int,
    @SerialName("nama_kategori")
    val namaKategori: String,
    @SerialName("deskripsi_kategori")
    val deskripsiKategori: String
)