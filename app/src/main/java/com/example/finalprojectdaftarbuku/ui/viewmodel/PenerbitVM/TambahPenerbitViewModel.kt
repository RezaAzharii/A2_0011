package com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Penerbit
import com.example.finalprojectdaftarbuku.repository.PenerbitRepository
import kotlinx.coroutines.launch
import okio.IOException

class TambahPenerbitViewModel(
    private val pnb: PenerbitRepository
):ViewModel(){
    var pnbUiState by mutableStateOf(PenerbitUiState())
        private set

    fun updateInsertPenerbitState(penerbitUiEvent: PenerbitUiEvent){
        pnbUiState = PenerbitUiState(penerbitUiEvent = penerbitUiEvent)
    }

    fun insertPnb(){
        viewModelScope.launch {
            try {
                pnb.insertPenerbit(pnbUiState.penerbitUiEvent.toPnb())
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
}

data class PenerbitUiState(
    val penerbitUiEvent: PenerbitUiEvent = PenerbitUiEvent()
)

data class PenerbitUiEvent(
    val idPenerbit: Int = 0,
    val namaPenerbit: String = "",
    val alamatPenerbit: String = "",
    val telpPenerbit: String = ""
)

fun PenerbitUiEvent.toPnb(): Penerbit = Penerbit(
    idPenerbit = idPenerbit,
    namaPenerbit = namaPenerbit,
    alamatPenerbit = alamatPenerbit,
    telpPenerbit = telpPenerbit
)

fun Penerbit.toUiStatePnb(): PenerbitUiState = PenerbitUiState(
    penerbitUiEvent = toPenerbitUiEvent()
)

fun Penerbit.toPenerbitUiEvent(): PenerbitUiEvent = PenerbitUiEvent(
    idPenerbit = idPenerbit,
    namaPenerbit = namaPenerbit,
    alamatPenerbit = alamatPenerbit,
    telpPenerbit = telpPenerbit
)