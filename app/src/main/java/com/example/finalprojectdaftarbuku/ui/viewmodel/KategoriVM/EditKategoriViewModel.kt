package com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.repository.KategoriRepository
import com.example.finalprojectdaftarbuku.ui.navigation.DestinasiUpdateKategori
import kotlinx.coroutines.launch

class EditKategoriViewModel(
    savedStateHandle: SavedStateHandle,
    private val ktg: KategoriRepository
): ViewModel(){
    var UpdateKtgUiState by mutableStateOf(KategoriUiState())
        private set
    private val _idk: Int = checkNotNull(savedStateHandle[DestinasiUpdateKategori.IDK])

    init {
        viewModelScope.launch {
            UpdateKtgUiState = ktg.getKategoriById(_idk)
                .toUiStateKtg()
        }
    }

    fun updateInsertKtgState(kategoriUiEvent: KategoriUiEvent){
        UpdateKtgUiState = KategoriUiState(kategoriUiEvent = kategoriUiEvent)
    }

    suspend fun updateKtg(){
        viewModelScope.launch {
            try {
                ktg.updateKategori(_idk, UpdateKtgUiState.kategoriUiEvent.toKtg())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}