package com.example.finalprojectdaftarbuku.di

import com.example.finalprojectdaftarbuku.repository.BukuRepository
import com.example.finalprojectdaftarbuku.repository.KategoriRepository
import com.example.finalprojectdaftarbuku.repository.NetworkBukuRepository
import com.example.finalprojectdaftarbuku.repository.NetworkKategoriRepository
import com.example.finalprojectdaftarbuku.repository.NetworkPenerbitRepository
import com.example.finalprojectdaftarbuku.repository.NetworkPenulisRepository
import com.example.finalprojectdaftarbuku.repository.PenerbitRepository
import com.example.finalprojectdaftarbuku.repository.PenulisRepository
import com.example.finalprojectdaftarbuku.serviceapi.BukuService
import com.example.finalprojectdaftarbuku.serviceapi.KategoriService
import com.example.finalprojectdaftarbuku.serviceapi.PenerbitService
import com.example.finalprojectdaftarbuku.serviceapi.PenulisService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val bukuRepository: BukuRepository
    val kategoriRepository: KategoriRepository
    val penerbitRepository: PenerbitRepository
    val penulisRepository: PenulisRepository
}

class ChillBookContainer(
) : AppContainer{
    private val baseUrl = "http://10.0.2.2:3000/api/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()
    private val bukuService: BukuService by lazy {
        retrofit.create(BukuService::class.java)
    }
    private val kategoriService: KategoriService by lazy {
        retrofit.create(KategoriService::class.java)
    }
    private val penerbitService: PenerbitService by lazy {
        retrofit.create(PenerbitService::class.java)
    }
    private val penulisService: PenulisService by lazy {
        retrofit.create(PenulisService::class.java)
    }

    override val bukuRepository: BukuRepository by lazy {
        NetworkBukuRepository(bukuService)
    }
    override val kategoriRepository: KategoriRepository by lazy {
        NetworkKategoriRepository(kategoriService)
    }
    override val penerbitRepository: PenerbitRepository by lazy {
        NetworkPenerbitRepository(penerbitService)
    }
    override val penulisRepository: PenulisRepository by lazy {
        NetworkPenulisRepository(penulisService)
    }
}