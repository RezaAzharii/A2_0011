package com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Kategori
import com.example.finalprojectdaftarbuku.repository.KategoriRepository
import kotlinx.coroutines.launch
import okio.IOException

class TambahKategoriViewModel(
    private val ktg: KategoriRepository
): ViewModel(){
    var ktgUiState by mutableStateOf(KategoriUiState())
        private set

    fun updateInsertKategoriState(kategoriUiEvent: KategoriUiEvent){
        ktgUiState = KategoriUiState(kategoriUiEvent = kategoriUiEvent)
    }

    fun insertKtg(){
        viewModelScope.launch {
            try {
                ktg.insertKategori(ktgUiState.kategoriUiEvent.toKtg())
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
}

data class KategoriUiState(
    val kategoriUiEvent: KategoriUiEvent = KategoriUiEvent()
)

data class KategoriUiEvent(
    val idKategori: Int = 0,
    val namaKategori: String = "",
    val deskripsiKategori: String = ""
)

fun KategoriUiEvent.toKtg(): Kategori = Kategori(
    idKategori = idKategori,
    namaKategori = namaKategori,
    deskripsiKategori = deskripsiKategori
)

fun Kategori.toUiStateKtg(): KategoriUiState = KategoriUiState(
    kategoriUiEvent = toKategoriUievent()
)

fun Kategori.toKategoriUievent(): KategoriUiEvent = KategoriUiEvent(
    idKategori = idKategori,
    namaKategori = namaKategori,
    deskripsiKategori = deskripsiKategori
)