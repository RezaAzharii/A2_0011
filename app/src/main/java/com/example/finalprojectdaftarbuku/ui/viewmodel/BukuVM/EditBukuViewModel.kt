package com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Kategori
import com.example.finalprojectdaftarbuku.model.Penerbit
import com.example.finalprojectdaftarbuku.model.Penulis
import com.example.finalprojectdaftarbuku.repository.BukuRepository
import com.example.finalprojectdaftarbuku.repository.KategoriRepository
import com.example.finalprojectdaftarbuku.repository.PenerbitRepository
import com.example.finalprojectdaftarbuku.repository.PenulisRepository
import com.example.finalprojectdaftarbuku.ui.navigation.DestinasiUpdateBuku
import kotlinx.coroutines.launch
import okio.IOException

class EditBukuViewModel(
    savedStateHandle: SavedStateHandle,
    private val bku: BukuRepository,
    private val ktg: KategoriRepository,
    private val pnb: PenerbitRepository,
    private val pls: PenulisRepository
):ViewModel(){
    var kategoriList by mutableStateOf(listOf<Kategori>())
        private set

    var penerbitList by mutableStateOf(listOf<Penerbit>())
        private set

    var penulisList by mutableStateOf(listOf<Penulis>())
        private set
    var updateBukuUiState by mutableStateOf(BukuUiState())
        private set
    private val _idBuku: Int = checkNotNull(savedStateHandle[DestinasiUpdateBuku.IDB])

    init {
        viewModelScope.launch {
            loadData()
            updateBukuUiState = bku.getBukubyID(_idBuku)
                .toUiStateBuku()
        }
    }

    fun updateInsertBkuState(bukuUiEvent: BukuUiEvent){
        updateBukuUiState = BukuUiState(bukuUiEvent = bukuUiEvent)
    }

    suspend fun updateBku(){
        viewModelScope.launch {
            try {
                bku.updateBuku(_idBuku, updateBukuUiState.bukuUiEvent.toBuku())
            }catch (e: IOException){
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