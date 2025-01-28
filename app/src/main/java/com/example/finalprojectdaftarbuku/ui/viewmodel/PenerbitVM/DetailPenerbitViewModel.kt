package com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Penerbit
import com.example.finalprojectdaftarbuku.repository.PenerbitRepository
import com.example.finalprojectdaftarbuku.ui.navigation.DestinasiDetailPenerbit
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailPnbUiState{
    data class Success(val penerbit: Penerbit): DetailPnbUiState()
    object Loading: DetailPnbUiState()
    object Error: DetailPnbUiState()
}

class DetailPenerbitViewModel(
    savedStateHandle: SavedStateHandle,
    private val pnb: PenerbitRepository
): ViewModel(){
    var penerbitDetailState: DetailPnbUiState by mutableStateOf(DetailPnbUiState.Loading)
        private set
    private val _idpn: Int = checkNotNull(savedStateHandle[DestinasiDetailPenerbit.IDPN])

    init {
        getPenerbitById()
    }

    fun getPenerbitById(){
        viewModelScope.launch {
            penerbitDetailState = DetailPnbUiState.Loading
            penerbitDetailState = try {
                val penerbit = pnb.getPenerbitById(_idpn)
                DetailPnbUiState.Success(penerbit)
            }catch (e: IOException){
                DetailPnbUiState.Error
            }
        }
    }
}