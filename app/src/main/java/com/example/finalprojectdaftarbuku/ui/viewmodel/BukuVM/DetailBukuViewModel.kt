package com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Buku
import com.example.finalprojectdaftarbuku.repository.BukuRepository
import com.example.finalprojectdaftarbuku.repository.KategoriRepository
import com.example.finalprojectdaftarbuku.repository.PenerbitRepository
import com.example.finalprojectdaftarbuku.repository.PenulisRepository
import com.example.finalprojectdaftarbuku.ui.navigation.DestinasiDetailBuku
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailBUiState{
    data class Success(val buku: Buku, val kategori: String, val penulis: String, val penerbit: String): DetailBUiState()
    object Error: DetailBUiState()
    object Loading: DetailBUiState()
}

class DetailBukuViewModel(
    savedStateHandle: SavedStateHandle,
    private val bku: BukuRepository,
    private val ktg: KategoriRepository,
    private val pnb: PenerbitRepository,
    private val pns: PenulisRepository
): ViewModel(){
    var bkuDetailState: DetailBUiState by mutableStateOf(DetailBUiState.Loading)
        private set
    private val _idb: Int = checkNotNull(savedStateHandle[DestinasiDetailBuku.IDB])

    init {
        getBukubyID()
    }

    fun getBukubyID(){
        viewModelScope.launch {
            bkuDetailState = DetailBUiState.Loading
            bkuDetailState = try {
                val buku = bku.getBukubyID(_idb)
                val kategori = ktg.getKategoriById(buku.idKategori).namaKategori
                val penulis = pns.getPenulisById(buku.idPenulis).namaPenulis
                val penerbit = pnb.getPenerbitById(buku.idPenerbit).namaPenerbit
                DetailBUiState.Success(
                    buku,
                    kategori = kategori,
                    penulis = penulis,
                    penerbit = penerbit
                )
            }catch (e: IOException){
                DetailBUiState.Error
            }
        }
    }
}