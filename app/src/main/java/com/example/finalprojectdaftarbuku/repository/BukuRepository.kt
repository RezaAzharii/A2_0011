package com.example.finalprojectdaftarbuku.repository

import com.example.finalprojectdaftarbuku.model.AllBukuResponse
import com.example.finalprojectdaftarbuku.model.Buku

interface BukuRepository{
    suspend fun getBuku(): AllBukuResponse
    suspend fun getSPBuku(): AllBukuResponse
    suspend fun getSHBuku(): AllBukuResponse
    suspend fun getBukubyID(idBuku: Int): Buku
    suspend fun insertBuku(buku: Buku)
    suspend fun updateBuku(idBuku: Int, buku: Buku)
    suspend fun deleteBuku(idBuku: Int)
}
