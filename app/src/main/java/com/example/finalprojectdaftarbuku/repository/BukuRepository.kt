package com.example.finalprojectdaftarbuku.repository

import com.example.finalprojectdaftarbuku.model.AllBukuResponse
import com.example.finalprojectdaftarbuku.model.Buku
import com.example.finalprojectdaftarbuku.serviceapi.BukuService
import okio.IOException

interface BukuRepository{
    suspend fun getBuku(): AllBukuResponse
    suspend fun getSPBuku(): AllBukuResponse
    suspend fun getSHBuku(): AllBukuResponse
    suspend fun getBukubyID(idBuku: Int): Buku
    suspend fun insertBuku(buku: Buku)
    suspend fun updateBuku(idBuku: Int, buku: Buku)
    suspend fun deleteBuku(idBuku: Int)
}

class NetworkBukuRepository(
    private val bukuApiService: BukuService
): BukuRepository{
    override suspend fun getBuku(): AllBukuResponse =
        bukuApiService.getAllBuku()

    override suspend fun getSPBuku(): AllBukuResponse =
        bukuApiService.getSPBuku()

    override suspend fun getSHBuku(): AllBukuResponse =
        bukuApiService.getSHBuku()

    override suspend fun getBukubyID(idBuku: Int): Buku {
        return bukuApiService.getBukubyID(idBuku).data
    }

    override suspend fun insertBuku(buku: Buku) {
        bukuApiService.insertBuku(buku)
    }

    override suspend fun updateBuku(idBuku: Int, buku: Buku) {
        bukuApiService.updateBuku(idBuku, buku)
    }

    override suspend fun deleteBuku(idBuku: Int) {
        try {
            val response = bukuApiService.deleteBuku(idBuku)
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