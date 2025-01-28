package com.example.finalprojectdaftarbuku.ui.view.Penerbit

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
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.PenerbitUiEvent
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.PenerbitUiState
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.TambahPenerbitViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun TambahPenerbitScreen(
    NavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TambahPenerbitViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        topBar = {
            TopBarApp(
                judul = "Masukan Data Penerbit",
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
            FormBodyPnb(
                penerbitUiState = viewModel.pnbUiState,
                onPenerbitChange = viewModel::updateInsertPenerbitState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.insertPnb()
                    }
                    NavigateBack()
                },
            )
        }
    }
}

@Composable
fun FormBodyPnb(
    penerbitUiState: PenerbitUiState,
    onPenerbitChange: (PenerbitUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            penerbitUiEvent = penerbitUiState.penerbitUiEvent,
            onValueChange = onPenerbitChange,
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
    penerbitUiEvent: PenerbitUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (PenerbitUiEvent) -> Unit = {},
    enabled: Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = penerbitUiEvent.namaPenerbit,
            onValueChange = {onValueChange(penerbitUiEvent.copy(namaPenerbit = it))},
            label = { Text("Nama Penerbit") },
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
            value = penerbitUiEvent.alamatPenerbit,
            onValueChange = {onValueChange(penerbitUiEvent.copy(alamatPenerbit = it))},
            label = { Text("Alamat") },
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
            value = penerbitUiEvent.telpPenerbit,
            onValueChange = {onValueChange(penerbitUiEvent.copy(telpPenerbit = it))},
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