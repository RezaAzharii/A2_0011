package com.example.finalprojectdaftarbuku.repository

import com.example.finalprojectdaftarbuku.model.AllPenerbitResponse
import com.example.finalprojectdaftarbuku.model.Penerbit
import com.example.finalprojectdaftarbuku.serviceapi.PenerbitService
import okio.IOException

interface PenerbitRepository{
    suspend fun getPenerbit(): AllPenerbitResponse
    suspend fun getPenerbitById(idPenerbit: Int): Penerbit
    suspend fun insertPenerbit(penerbit: Penerbit)
    suspend fun updatePenerbit(idPenerbit: Int, penerbit: Penerbit)
    suspend fun deletePenerbit(idPenerbit: Int)
}

data class NetworkPenerbitRepository(
    private val penerbitService: PenerbitService
): PenerbitRepository{
    override suspend fun getPenerbit(): AllPenerbitResponse =
        penerbitService.getAllPenerbit()

    override suspend fun getPenerbitById(idPenerbit: Int): Penerbit {
        return penerbitService.getPenerbitbyID(idPenerbit).data
    }

    override suspend fun insertPenerbit(penerbit: Penerbit) {
        penerbitService.insertPenerbit(penerbit)
    }

    override suspend fun updatePenerbit(idPenerbit: Int, penerbit: Penerbit) {
        penerbitService.updatePenerbit(idPenerbit, penerbit)
    }

    override suspend fun deletePenerbit(idPenerbit: Int) {
        try {
            val response = penerbitService.deletePenerbit(idPenerbit)
            if (!response.isSuccessful){
                throw IOException("Gagal menghapus Buku. Http Status code: " +
                        "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e: Exception){
            throw e
        }
    }

}