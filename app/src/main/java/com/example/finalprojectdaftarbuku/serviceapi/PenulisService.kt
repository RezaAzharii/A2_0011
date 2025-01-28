package com.example.finalprojectdaftarbuku.serviceapi

import com.example.finalprojectdaftarbuku.model.AllPenulisResponse
import com.example.finalprojectdaftarbuku.model.Penulis
import com.example.finalprojectdaftarbuku.model.PenulisDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PenulisService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("penulis/.")
    suspend fun getAllPenulis(): AllPenulisResponse

    @GET("penulis/{id_penulis}")
    suspend fun getPenulisById(@Path("id_penulis")idPenulis: Int): PenulisDetailResponse

    @POST("penulis/tambah-penulis")
    suspend fun insertPenulis(@Body penulis: Penulis)

    @PUT("penulis/{id_penulis}")
    suspend fun updatePenulis(@Path("id_penulis")idPenulis: Int, @Body penulis: Penulis)

    @DELETE("penulis/{id_penulis}")
    suspend fun deletePenulis(@Path("id_penulis")idPenulis: Int): Response<Void>
}