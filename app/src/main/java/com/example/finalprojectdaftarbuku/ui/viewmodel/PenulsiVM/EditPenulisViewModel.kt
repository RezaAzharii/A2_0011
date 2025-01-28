package com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.repository.PenulisRepository
import com.example.finalprojectdaftarbuku.ui.navigation.DestinasiUpdatePenulis
import kotlinx.coroutines.launch
import okio.IOException

class EditPenulisViewModel (
    savedStateHandle: SavedStateHandle,
    private val pns: PenulisRepository
):ViewModel(){
    var updatePnsUiState by mutableStateOf(PenulisUiState())
        private set
    private val _pns: Int = checkNotNull(savedStateHandle[DestinasiUpdatePenulis.IDPS])

    init {
        viewModelScope.launch {
            updatePnsUiState = pns.getPenulisById(_pns)
                .toUiStatePns()
        }
    }

    fun updateInsertPnsState(penulisUiEvent: PenulisUiEvent){
        updatePnsUiState = PenulisUiState(penulisUiEvent = penulisUiEvent)
    }

    suspend fun updatePns(){
        viewModelScope.launch {
            try {
                pns.updatePenulis(_pns, updatePnsUiState.penulisUiEvent.toPns())
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
}