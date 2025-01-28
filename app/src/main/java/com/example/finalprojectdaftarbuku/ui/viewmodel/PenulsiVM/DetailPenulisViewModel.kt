package com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Penulis
import com.example.finalprojectdaftarbuku.repository.PenulisRepository
import com.example.finalprojectdaftarbuku.ui.navigation.DestinasiDetailPenulis
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailPnsUiState{
    data class Success(val penulis: Penulis): DetailPnsUiState()
    object Loading: DetailPnsUiState()
    object Error: DetailPnsUiState()
}

class DetailPenulisViewModel(
    savedStateHandle: SavedStateHandle,
    private val pns: PenulisRepository
): ViewModel(){
    var penulisDetailState: DetailPnsUiState by mutableStateOf(DetailPnsUiState.Loading)
        private set
    private val _idpns: Int = checkNotNull(savedStateHandle[DestinasiDetailPenulis.IDPS])

    init {
        getPenulisById()
    }

    fun getPenulisById(){
        viewModelScope.launch {
            penulisDetailState = DetailPnsUiState.Loading
            penulisDetailState = try {
                val penulis = pns.getPenulisById(_idpns)
                DetailPnsUiState.Success(penulis)
            }catch (e: IOException){
                DetailPnsUiState.Error
            }
        }
    }
}