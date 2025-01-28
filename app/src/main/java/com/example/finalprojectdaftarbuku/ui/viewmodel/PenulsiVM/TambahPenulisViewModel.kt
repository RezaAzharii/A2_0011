package com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectdaftarbuku.model.Penulis
import com.example.finalprojectdaftarbuku.repository.PenulisRepository
import kotlinx.coroutines.launch
import okio.IOException

class TambahPenulisViewModel (
    private val pns: PenulisRepository
):ViewModel(){
    var pnsUiState by mutableStateOf(PenulisUiState())
        private set

    fun updateInsertPenulisState(peenulisUiEvent: PenulisUiEvent){
        pnsUiState = PenulisUiState(penulisUiEvent = peenulisUiEvent)
    }

    fun insertPns(){
        viewModelScope.launch {
            try {
                pns.insertPenulis(pnsUiState.penulisUiEvent.toPns())
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
}

data class PenulisUiState(
    val penulisUiEvent: PenulisUiEvent = PenulisUiEvent()
)

data class PenulisUiEvent(
    val idPenulis: Int = 0,
    val namaPenulis: String = "",
    val biografi: String = "",
    val kontak: String = ""
)

fun PenulisUiEvent.toPns(): Penulis = Penulis(
    idPenulis = idPenulis,
    namaPenulis = namaPenulis,
    biografi = biografi,
    kontak = kontak
)

fun Penulis.toUiStatePns(): PenulisUiState = PenulisUiState(
    penulisUiEvent = toPenulisUiEvent()
)

fun Penulis.toPenulisUiEvent(): PenulisUiEvent = PenulisUiEvent(
    idPenulis = idPenulis,
    namaPenulis = namaPenulis,
    biografi = biografi,
    kontak = kontak
)