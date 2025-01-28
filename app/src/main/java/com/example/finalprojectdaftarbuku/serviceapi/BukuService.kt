package com.example.finalprojectdaftarbuku.serviceapi

import com.example.finalprojectdaftarbuku.model.AllBukuResponse
import com.example.finalprojectdaftarbuku.model.Buku
import com.example.finalprojectdaftarbuku.model.BukuDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BukuService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @POST("buku/tambahbuku")
    suspend fun insertBuku(@Body buku: Buku)

    @GET("buku/tersedia")
    suspend fun getAllBuku() : AllBukuResponse

    @GET("buku/bkupesan")
    suspend fun getSPBuku(): AllBukuResponse

    @GET("buku/bkuhabis")
    suspend fun getSHBuku(): AllBukuResponse

    @GET("buku/{id_buku}")
    suspend fun getBukubyID(@Path("id_buku")idBuku: Int): BukuDetailResponse

    @PUT("buku/{id_buku}")
    suspend fun updateBuku(@Path("id_buku")idBuku: Int, @Body buku: Buku)

    @DELETE("buku/{id_buku}")
    suspend fun deleteBuku(@Path("id_buku")idBuku: Int): Response<Void>
}