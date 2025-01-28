package com.example.finalprojectdaftarbuku.serviceapi

import com.example.finalprojectdaftarbuku.model.ALlKategoriResponse
import com.example.finalprojectdaftarbuku.model.BukuDetailResponse
import com.example.finalprojectdaftarbuku.model.Kategori
import com.example.finalprojectdaftarbuku.model.KategoriDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KategoriService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("kategori/.")
    suspend fun getAllKategori() : ALlKategoriResponse

    @GET("kategori/{id_kategori}")
    suspend fun getKategoribyID(@Path("id_kategori")idKategori: Int): KategoriDetailResponse

    @POST("kategori/tambahkategori")
    suspend fun insertKategori(@Body kategori: Kategori)

    @DELETE("kategori/{id_kategori}")
    suspend fun deleteKategori(@Path("id_kategori") idKategori: Int): Response<Void>

    @PUT("kategori/{id_kategori}")
    suspend fun updateKategori(@Path("id_kategori")idKategori: Int, @Body kategori: Kategori)
}