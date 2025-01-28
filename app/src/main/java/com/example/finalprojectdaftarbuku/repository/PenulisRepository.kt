package com.example.finalprojectdaftarbuku.repository

import com.example.finalprojectdaftarbuku.model.AllPenulisResponse
import com.example.finalprojectdaftarbuku.model.Penulis
import com.example.finalprojectdaftarbuku.serviceapi.PenulisService
import okio.IOException

interface PenulisRepository {
    suspend fun getPenulis(): AllPenulisResponse
    suspend fun getPenulisById(idPenulis: Int): Penulis
    suspend fun insertPenulis(penulis: Penulis)
    suspend fun updatePenulis(idPenulis: Int, penulis: Penulis)
    suspend fun deletePenulis(idPenulis: Int)
}

data class NetworkPenulisRepository(
    private val penulisService: PenulisService
): PenulisRepository{
    override suspend fun getPenulis(): AllPenulisResponse =
        penulisService.getAllPenulis()

    override suspend fun getPenulisById(idPenulis: Int): Penulis {
        return penulisService.getPenulisById(idPenulis).data
    }

    override suspend fun insertPenulis(penulis: Penulis) {
        penulisService.insertPenulis(penulis)
    }

    override suspend fun updatePenulis(idPenulis: Int, penulis: Penulis) {
        penulisService.updatePenulis(idPenulis, penulis)
    }

    override suspend fun deletePenulis(idPenulis: Int) {
        try {
            val response = penulisService.deletePenulis(idPenulis)
            if (!response.isSuccessful){
                throw IOException("Gagal menghapus Penulis. Http Status code: " +
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