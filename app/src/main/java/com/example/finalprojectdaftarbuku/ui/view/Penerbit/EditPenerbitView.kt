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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectdaftarbuku.R
import com.example.finalprojectdaftarbuku.ui.customwidget.TopBarApp
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.EditPenerbitViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.PenerbitUiEvent
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.PenerbitUiState
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun EditPenerbitView(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: EditPenerbitViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        topBar = {
            TopBarApp(
                judul = "Edit Data Penerbit",
                onBack = onBack,
                showBackButton = true
            )
        }
    ){innerpadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.creme2))
                .padding(innerpadding)
                .verticalScroll(rememberScrollState())
        ) {
            FormEditBodyPnb(
                penerbitUiState = viewModel.updatePnbUiState,
                onPenerbitChange = viewModel::updateInsertPnbState,
                onDeleteClick = {
                    viewModel.deletePenerbit()
                    onNavigate()
                } ,
                onSaveClick ={
                    coroutineScope.launch {
                        viewModel.updatePnb()
                        delay(600)
                        withContext(Dispatchers.Main){
                            onNavigate()
                        }
                    }
                }
            )
        }

    }
}

@Composable
fun FormEditBodyPnb(
    penerbitUiState: PenerbitUiState,
    onPenerbitChange: (PenerbitUiEvent) -> Unit,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    Column (
        verticalArrangement = Arrangement.spacedBy(4.dp),
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
        Button(
            onClick = { deleteConfirmationRequired = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.softgreen),
                contentColor = Color.White
            )
        ) {
            Text("Hapus")
        }
    }
    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDeleteClick()
            },
            onDeleteCancel = {
                deleteConfirmationRequired = false
            }, modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDeleteCancel,
        title = { Text("Konfirmasi Hapus") },
        text = { Text("Apakah Anda yakin ingin menghapus penerbit ini?") },
        modifier = modifier,
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text("Ya, Hapus")
            }
        },
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text("Batal")
            }
        }
    )
}