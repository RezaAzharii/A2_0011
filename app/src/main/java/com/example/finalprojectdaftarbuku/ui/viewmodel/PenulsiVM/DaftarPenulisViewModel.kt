package com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Penulis
import com.example.finalprojectdaftarbuku.repository.PenulisRepository
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.DaftarKategoriUiState
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed class DaftarPenulisUiState{
    data class Success(val dataPns: List<Penulis>): DaftarPenulisUiState()
    object Loading: DaftarPenulisUiState()
    object Error: DaftarPenulisUiState()
}

class DaftarPenulisViewModel (
    private val pns: PenulisRepository
): ViewModel(){
    var pnsUiState: DaftarPenulisUiState by mutableStateOf(DaftarPenulisUiState.Loading)
        private set

    init {
        getPenulis()
    }

    fun getPenulis(){
        viewModelScope.launch {
            pnsUiState = DaftarPenulisUiState.Loading
            pnsUiState = try {
                DaftarPenulisUiState.Success(pns.getPenulis().data)
            }catch (e: IOException){
                DaftarPenulisUiState.Error
            }
        }
    }

    fun deletePenulis(idPenulis: Int){
        viewModelScope.launch {
            try {
                pns.deletePenulis(idPenulis)
            }catch (e: IOException){
                DaftarKategoriUiState.Error
            }catch (e: HttpException){
                DaftarKategoriUiState.Error
            }
        }
    }
}