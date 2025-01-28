package com.example.finalprojectdaftarbuku.repository

import com.example.finalprojectdaftarbuku.model.ALlKategoriResponse
import com.example.finalprojectdaftarbuku.model.Kategori
import com.example.finalprojectdaftarbuku.serviceapi.KategoriService
import java.io.IOException

interface KategoriRepository {
    suspend fun getKategori(): ALlKategoriResponse
    suspend fun getKategoriById(idKategori: Int): Kategori
    suspend fun insertKategori(kategori: Kategori)
    suspend fun updateKategori(idKategori: Int, kategori: Kategori)
    suspend fun deleteKategori(idKategori: Int)
}

data class NetworkKategoriRepository(
    private val kategoriService: KategoriService
): KategoriRepository{
    override suspend fun getKategori(): ALlKategoriResponse =
        kategoriService.getAllKategori()

    override suspend fun getKategoriById(idKategori: Int): Kategori{
        return kategoriService.getKategoribyID(idKategori).data
    }

    override suspend fun insertKategori(kategori: Kategori) {
        kategoriService.insertKategori(kategori)
    }

    override suspend fun updateKategori(idKategori: Int, kategori: Kategori) {
        kategoriService.updateKategori(idKategori, kategori)
    }

    override suspend fun deleteKategori(idKategori: Int) {
        try {
            val response = kategoriService.deleteKategori(idKategori)
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