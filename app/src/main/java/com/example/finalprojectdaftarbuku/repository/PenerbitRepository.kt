package com.example.finalprojectdaftarbuku.repository

import com.example.finalprojectdaftarbuku.model.AllPenerbitResponse
import com.example.finalprojectdaftarbuku.model.Penerbit

interface PenerbitRepository{
    suspend fun getPenerbit(): AllPenerbitResponse
    suspend fun getPenerbitById(idPenerbit: Int): Penerbit
    suspend fun insertPenerbit(penerbit: Penerbit)
    suspend fun updatePenerbit(idPenerbit: Int, penerbit: Penerbit)
    suspend fun deletePenerbit(idPenerbit: Int)
}