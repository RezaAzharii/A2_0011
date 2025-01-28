package com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Buku
import com.example.finalprojectdaftarbuku.repository.BukuRepository
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.DaftarKategoriUiState
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException


sealed class HomeUiState{
    data class Success(val buku: List<Buku>): HomeUiState()
    object Error: HomeUiState()
    object Loading: HomeUiState()
}

class HomeViewModel(
    private val bku: BukuRepository
): ViewModel(){
    var bkuUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getBuku()
    }

    fun getBuku() {
        viewModelScope.launch {
            bkuUiState = HomeUiState.Loading
            bkuUiState = try {
                HomeUiState.Success(bku.getBuku().data)
            } catch (e: IOException) {
                HomeUiState.Error
            }
        }
    }

    fun deleteBuku(idBuku: Int){
        viewModelScope.launch {
            try {
                bku.deleteBuku(idBuku)
            }catch (e: IOException){
                DaftarKategoriUiState.Error
            }catch (e: HttpException){
                DaftarKategoriUiState.Error
            }
        }
    }
}