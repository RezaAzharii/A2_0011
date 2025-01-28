package com.example.finalprojectdaftarbuku.ui.view.Penulis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectdaftarbuku.R
import com.example.finalprojectdaftarbuku.ui.customwidget.TopBarApp
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM.PenulisUiEvent
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM.PenulisUiState
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM.TambahPenulisViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun TambahPenulisScreen(
    NavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TambahPenulisViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        topBar = {
            TopBarApp(
                judul = "Masukan Data Penulis",
                onBack = NavigateBack,
                showBackButton = true
            )
        }
    ){innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.creme2))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            FormBodyPns(
                penulisUiState = viewModel.pnsUiState,
                onPenulisChange = viewModel::updateInsertPenulisState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.insertPns()
                    }
                    NavigateBack()
                }
            )
        }
    }
}

@Composable
fun FormBodyPns(
    penulisUiState: PenulisUiState,
    onPenulisChange: (PenulisUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            penulisUiEvent = penulisUiState.penulisUiEvent,
            onValueChange = onPenulisChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.green),
                contentColor = Color.White
            )
        ) {
            Text("Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    penulisUiEvent: PenulisUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (PenulisUiEvent) -> Unit = {},
    enabled: Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = penulisUiEvent.namaPenulis,
            onValueChange = {onValueChange(penulisUiEvent.copy(namaPenulis = it))},
            label = { Text("Nama Penulis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(R.color.creme2),
                unfocusedBorderColor = colorResource(R.color.creme2),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = colorResource(R.color.black),
                containerColor = Color.White
            )
        )
        OutlinedTextField(
            value = penulisUiEvent.biografi,
            onValueChange = {onValueChange(penulisUiEvent.copy(biografi = it))},
            label = { Text("Biografi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(R.color.creme2),
                unfocusedBorderColor = colorResource(R.color.creme2),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = colorResource(R.color.black),
                containerColor = Color.White
            )
        )
        OutlinedTextField(
            value = penulisUiEvent.kontak,
            onValueChange = {onValueChange(penulisUiEvent.copy(kontak = it))},
            label = { Text("No Handphone") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(R.color.creme2),
                unfocusedBorderColor = colorResource(R.color.creme2),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = colorResource(R.color.black),
                containerColor = Color.White
            )
        )
        if (enabled){
            Text(
                text = "isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}