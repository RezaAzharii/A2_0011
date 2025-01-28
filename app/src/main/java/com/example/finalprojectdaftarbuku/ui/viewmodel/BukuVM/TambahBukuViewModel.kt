package com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Buku
import com.example.finalprojectdaftarbuku.model.Kategori
import com.example.finalprojectdaftarbuku.model.Penerbit
import com.example.finalprojectdaftarbuku.model.Penulis
import com.example.finalprojectdaftarbuku.repository.BukuRepository
import com.example.finalprojectdaftarbuku.repository.KategoriRepository
import com.example.finalprojectdaftarbuku.repository.PenerbitRepository
import com.example.finalprojectdaftarbuku.repository.PenulisRepository
import kotlinx.coroutines.launch

class TambahBukuViewModel(
    private val bku: BukuRepository,
    private val ktg: KategoriRepository,
    private val pnb: PenerbitRepository,
    private val pls: PenulisRepository
): ViewModel(){
    var bkuiState by mutableStateOf(BukuUiState())
        private set

    var kategoriList by mutableStateOf(listOf<Kategori>())
        private set

    var penerbitList by mutableStateOf(listOf<Penerbit>())
        private set

    var penulisList by mutableStateOf(listOf<Penulis>())
        private set

    init {
        loadData()
    }
    fun updateInsertBkuState(bukuUiEvent: BukuUiEvent){
        bkuiState = BukuUiState(bukuUiEvent = bukuUiEvent)
    }

    suspend fun insertBku(){
        viewModelScope.launch {
            try {
                bku.insertBuku(bkuiState.bukuUiEvent.toBuku())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun loadData(){
        viewModelScope.launch {
            try {
                kategoriList = ktg.getKategori().data
                penerbitList = pnb.getPenerbit().data
                penulisList = pls.getPenulis().data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class BukuUiState(
    val bukuUiEvent: BukuUiEvent = BukuUiEvent()
)

data class BukuUiEvent(
    val idBuku: Int = 0,
    val namaBuku: String = "",
    val deskripsiBuku: String = "",
    val tanggalTerbit: String = "",
    val statusBuku: String = "",
    val idKategori: Int = 0,
    val idPenerbit: Int = 0,
    val idPenulis: Int = 0
)

fun BukuUiEvent.toBuku(): Buku = Buku(
    idBuku = idBuku,
    namaBuku = namaBuku,
    deskripsiBuku = deskripsiBuku,
    tanggalTerbit = tanggalTerbit,
    statusBuku = statusBuku,
    idKategori = idKategori,
    idPenerbit = idPenerbit,
    idPenulis = idPenulis
)

fun Buku.toUiStateBuku(): BukuUiState = BukuUiState(
    bukuUiEvent = toBukuUiEvent()
)

fun Buku.toBukuUiEvent(): BukuUiEvent = BukuUiEvent(
    idBuku = idBuku,
    namaBuku = namaBuku,
    deskripsiBuku = deskripsiBuku,
    tanggalTerbit = tanggalTerbit,
    statusBuku = statusBuku,
    idKategori = idKategori,
    idPenerbit = idPenerbit,
    idPenulis = idPenulis
)