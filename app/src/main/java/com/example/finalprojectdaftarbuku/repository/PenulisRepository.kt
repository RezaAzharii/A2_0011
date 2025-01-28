package com.example.finalprojectdaftarbuku.repository

import com.example.finalprojectdaftarbuku.model.AllPenulisResponse
import com.example.finalprojectdaftarbuku.model.Penulis

interface PenulisRepository {
    suspend fun getPenulis(): AllPenulisResponse
    suspend fun getPenulisById(idPenulis: Int): Penulis
    suspend fun insertPenulis(penulis: Penulis)
    suspend fun updatePenulis(idPenulis: Int, penulis: Penulis)
    suspend fun deletePenulis(idPenulis: Int)
}