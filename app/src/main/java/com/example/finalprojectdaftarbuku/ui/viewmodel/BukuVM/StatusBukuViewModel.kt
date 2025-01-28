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

sealed class StatusBUiState{
    data class Success(val buku: List<Buku>): StatusBUiState()
    object Error: StatusBUiState()
    object Loading: StatusBUiState()
}

class StatusBukuViewModel(
    private val bku: BukuRepository
):ViewModel(){
    var statUiState: StatusBUiState by mutableStateOf(StatusBUiState.Loading)
        private set

    init {
        getPsBuku()
    }

    fun getPsBuku(){
        viewModelScope.launch {
            statUiState = StatusBUiState.Loading
            statUiState = try {
                StatusBUiState.Success(bku.getSPBuku().data)
            }catch (e: IOException){
                StatusBUiState.Error
            }
        }
    }

    fun getPhBuku(){
        viewModelScope.launch {
            statUiState = StatusBUiState.Loading
            statUiState = try {
                StatusBUiState.Success(bku.getSHBuku().data)
            }catch (e: IOException){
                StatusBUiState.Error
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