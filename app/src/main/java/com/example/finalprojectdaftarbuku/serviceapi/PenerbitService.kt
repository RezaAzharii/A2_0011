package com.example.finalprojectdaftarbuku.serviceapi

import com.example.finalprojectdaftarbuku.model.AllPenerbitResponse
import com.example.finalprojectdaftarbuku.model.Penerbit
import com.example.finalprojectdaftarbuku.model.PenerbitDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PenerbitService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("penerbit/.")
    suspend fun getAllPenerbit(): AllPenerbitResponse

    @GET("penerbit/{id_penerbit}")
    suspend fun getPenerbitbyID(@Path("id_penerbit")idPenerbit: Int): PenerbitDetailResponse

    @POST("penerbit/tambah-penerbit")
    suspend fun insertPenerbit(@Body penerbit: Penerbit)

    @PUT("penerbit/{id_penerbit}")
    suspend fun updatePenerbit(@Path("id_penerbit")idPenerbit: Int, @Body penerbit: Penerbit)

    @DELETE("penerbit/{id_penerbit}")
    suspend fun deletePenerbit(@Path("id_penerbit") idPenerbit: Int): Response<Void>
}