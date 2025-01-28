package com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Penerbit
import com.example.finalprojectdaftarbuku.repository.PenerbitRepository
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.DaftarKategoriUiState
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed class DaftarPenerbitUiState{
    data class Success(val dataKtg: List<Penerbit>): DaftarPenerbitUiState()
    object Loading: DaftarPenerbitUiState()
    object Error: DaftarPenerbitUiState()
}

class DaftarPenerbitViewModel(
    private val pnb: PenerbitRepository
): ViewModel(){
    var pnbUiState: DaftarPenerbitUiState by mutableStateOf(DaftarPenerbitUiState.Loading)
        private set

    init {
        getPenerbit()
    }

    fun getPenerbit(){
        viewModelScope.launch {
            pnbUiState = DaftarPenerbitUiState.Loading
            pnbUiState = try {
                DaftarPenerbitUiState.Success(pnb.getPenerbit().data)
            }catch (e: IOException){
                DaftarPenerbitUiState.Error
            }
        }
    }

    fun deletePenerbit(idPenerbit: Int){
        viewModelScope.launch {
            try {
                pnb.deletePenerbit(idPenerbit)
            }catch (e: IOException){
                DaftarKategoriUiState.Error
            }catch (e: HttpException){
                DaftarKategoriUiState.Error
            }
        }
    }
}