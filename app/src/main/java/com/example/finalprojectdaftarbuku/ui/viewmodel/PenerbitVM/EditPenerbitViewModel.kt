package com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.repository.PenerbitRepository
import com.example.finalprojectdaftarbuku.ui.navigation.DestinasiUpdatePenerbit
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.DaftarKategoriUiState
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class EditPenerbitViewModel(
    savedStateHadle: SavedStateHandle,
    private val pnb: PenerbitRepository
):ViewModel(){
    var updatePnbUiState by mutableStateOf(PenerbitUiState())
        private set

    private val _idPnb: Int = checkNotNull(savedStateHadle[DestinasiUpdatePenerbit.IDPN])

    init {
        viewModelScope.launch {
            updatePnbUiState = pnb.getPenerbitById(_idPnb)
                .toUiStatePnb()
        }
    }

    fun updateInsertPnbState(penerbitUiEvent: PenerbitUiEvent){
        updatePnbUiState = PenerbitUiState(penerbitUiEvent = penerbitUiEvent)
    }

    suspend fun updatePnb(){
        viewModelScope.launch {
            try {
                pnb.updatePenerbit(_idPnb, updatePnbUiState.penerbitUiEvent.toPnb())
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }

    fun deletePenerbit(){
        viewModelScope.launch {
            try {
                pnb.deletePenerbit(_idPnb)
            }catch (e: IOException){
                DaftarKategoriUiState.Error
            }catch (e: HttpException){
                DaftarKategoriUiState.Error
            }
        }
    }
}