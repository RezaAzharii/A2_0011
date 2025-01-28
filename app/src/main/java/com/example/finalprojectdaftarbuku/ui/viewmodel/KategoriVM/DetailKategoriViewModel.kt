package com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Kategori
import com.example.finalprojectdaftarbuku.repository.KategoriRepository
import com.example.finalprojectdaftarbuku.ui.navigation.DestinasiDetailKategori
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailKtgUiState{
    data class Success(val kategori: Kategori) : DetailKtgUiState()
    object Error:DetailKtgUiState()
    object Loading: DetailKtgUiState()
}

class DetailKategoriViewModel(
    savedStateHandle: SavedStateHandle,
    private val ktg: KategoriRepository
):ViewModel(){
    var kategoriDetailState: DetailKtgUiState by mutableStateOf(DetailKtgUiState.Loading)
        private set

    private val _idk: Int = checkNotNull(savedStateHandle[DestinasiDetailKategori.IDK])

    init {
        getKategoriById()
    }

    fun getKategoriById(){
        viewModelScope.launch {
            kategoriDetailState = DetailKtgUiState.Loading
            kategoriDetailState = try {
                val kategori = ktg.getKategoriById(_idk)
                DetailKtgUiState.Success(kategori)
            }catch (e: IOException){
                DetailKtgUiState.Error
            }
        }
    }
}