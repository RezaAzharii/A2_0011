package com.example.finalprojectdaftarbuku.repository

import com.example.finalprojectdaftarbuku.model.ALlKategoriResponse
import com.example.finalprojectdaftarbuku.model.Kategori

interface KategoriRepository {
    suspend fun getKategori(): ALlKategoriResponse
    suspend fun getKategoriById(idKategori: Int): Kategori
    suspend fun insertKategori(kategori: Kategori)
    suspend fun updateKategori(idKategori: Int, kategori: Kategori)
    suspend fun deleteKategori(idKategori: Int)
}