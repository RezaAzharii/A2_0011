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
import retrofit2.HttpException

sealed class DaftarKategoriUiState{
    data class Success(val kategori: List<Kategori>) : DaftarKategoriUiState()
    object Loading: DaftarKategoriUiState()
    object Error: DaftarKategoriUiState()
}

class DaftarKategoriViewModel (
    private val ktg: KategoriRepository
): ViewModel(){
    var ktgUiState: DaftarKategoriUiState by mutableStateOf(DaftarKategoriUiState.Loading)
        private set

    init {
        getKategori()
    }

    fun getKategori(){
        viewModelScope.launch {
            ktgUiState = DaftarKategoriUiState.Loading
            ktgUiState = try {
                DaftarKategoriUiState.Success(ktg.getKategori().data)
            }catch (e: IOException){
                DaftarKategoriUiState.Error
            }
        }
    }

    fun deleteKategori(idKategori: Int){
        viewModelScope.launch {
            try {
                ktg.deleteKategori(idKategori)
            }catch (e: IOException){
                DaftarKategoriUiState.Error
            }catch (e: HttpException){
                DaftarKategoriUiState.Error
            }
        }
    }
}