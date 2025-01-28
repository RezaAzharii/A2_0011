package com.example.finalprojectdaftarbuku.ui.view.Buku

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectdaftarbuku.R
import com.example.finalprojectdaftarbuku.model.Kategori
import com.example.finalprojectdaftarbuku.model.Penerbit
import com.example.finalprojectdaftarbuku.model.Penulis
import com.example.finalprojectdaftarbuku.ui.customwidget.TopBarApp
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.BukuUiEvent
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.BukuUiState
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.TambahBukuViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBukuScreen(
    NavigateBack:() -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TambahBukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBarApp(
                judul = "Masukan Data Buku",
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
            EntryBodyBuku(
                bukuUiState = viewModel.bkuiState,
                kategoriList = viewModel.kategoriList,
                penerbitList = viewModel.penerbitList,
                penulisList = viewModel.penulisList,
                onBukuValueChange = viewModel::updateInsertBkuState,
                onSaveClick ={
                    coroutineScope.launch {
                        viewModel.insertBku()
                    }
                    NavigateBack()
                },

            )
        }
    }
}

@Composable
fun EntryBodyBuku(
    bukuUiState: BukuUiState,
    kategoriList: List<Kategori>,
    penerbitList: List<Penerbit>,
    penulisList: List<Penulis>,
    onBukuValueChange: (BukuUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        FormInputBuku(
            bukuUiEvent = bukuUiState.bukuUiEvent,
            kategoriList = kategoriList,
            penerbitList = penerbitList,
            penulisList = penulisList,
            onValueChange = onBukuValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.medium,
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
fun FormInputBuku(
    bukuUiEvent: BukuUiEvent,
    kategoriList: List<Kategori>,
    penerbitList: List<Penerbit>,
    penulisList: List<Penulis>,
    modifier: Modifier = Modifier,
    onValueChange: (BukuUiEvent) -> Unit = {},
    enabled: Boolean = true
){
    Column (
        modifier = Modifier
            .padding(start = 10.dp,end = 10.dp, top = 6.dp, bottom = 6.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        var statusExpanded by remember { mutableStateOf(false) }
        var kategoriExpanded by remember { mutableStateOf(false )}
        var penerbitExpanded by remember { mutableStateOf(false )}
        var penulisExpanded by remember { mutableStateOf(false )}
        val liststatus = listOf("Tersedia", "DiPesan", "Habis")

        OutlinedTextField(
            value = bukuUiEvent.namaBuku,
            onValueChange = {onValueChange(bukuUiEvent.copy(namaBuku = it))},
            label = { Text("Judul Buku") },
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
            value = bukuUiEvent.deskripsiBuku,
            onValueChange = { onValueChange(bukuUiEvent.copy(deskripsiBuku = it)) },
            label = { Text("Deskripsi Buku") },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp, max = 200.dp),
            enabled = enabled,
            singleLine = false,
            maxLines = 5,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(R.color.creme2),
                unfocusedBorderColor = colorResource(R.color.creme2),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = colorResource(R.color.black),
                containerColor = Color.White
            )
        )
        OutlinedTextField(
            value = bukuUiEvent.tanggalTerbit,
            onValueChange = {onValueChange(bukuUiEvent.copy(tanggalTerbit = it))},
            label = { Text("Tanggal Terbit") },
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
        ExposedDropdownMenuBox (
            expanded = statusExpanded,
            onExpandedChange = {statusExpanded = !statusExpanded}
        ){
            OutlinedTextField(
                value = bukuUiEvent.statusBuku,
                readOnly = true,
                onValueChange = {},
                label = { Text("Status Buku") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = statusExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(R.color.creme2),
                    unfocusedBorderColor = colorResource(R.color.creme2),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = colorResource(R.color.black),
                    containerColor = Color.White
                )
            )
            ExposedDropdownMenu(
                expanded = statusExpanded,
                onDismissRequest = {statusExpanded = false}
            ) {
                liststatus.forEach { ls ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(bukuUiEvent.copy(statusBuku = ls))
                            statusExpanded = false
                        },
                        text = { Text(ls) }
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = kategoriExpanded,
            onExpandedChange = {kategoriExpanded = !kategoriExpanded}
        ) {
            OutlinedTextField(
                value = kategoriList.find { it.idKategori == bukuUiEvent.idKategori }?.namaKategori ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Kategori") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = kategoriExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(R.color.creme2),
                    unfocusedBorderColor = colorResource(R.color.creme2),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = colorResource(R.color.black),
                    containerColor = Color.White
                )
            )
            ExposedDropdownMenu(
                expanded = kategoriExpanded,
                onDismissRequest = {kategoriExpanded = false}
            ) {
                kategoriList.forEach { kategori ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(bukuUiEvent.copy(idKategori = kategori.idKategori))
                            kategoriExpanded = false
                        },
                        text = { Text(kategori.namaKategori) }
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = penerbitExpanded,
            onExpandedChange = {penerbitExpanded = !penerbitExpanded}
        ) {
            OutlinedTextField(
                value = penerbitList.find { it.idPenerbit == bukuUiEvent.idPenerbit }?.namaPenerbit ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Penerbit") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = penerbitExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(R.color.creme2),
                    unfocusedBorderColor = colorResource(R.color.creme2),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = colorResource(R.color.black),
                    containerColor = Color.White
                )
            )
            ExposedDropdownMenu(
                expanded = penerbitExpanded,
                onDismissRequest = {penerbitExpanded = false}
            ) {
                penerbitList.forEach { penerbit ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(bukuUiEvent.copy(idPenerbit = penerbit.idPenerbit))
                            penerbitExpanded = false
                        },
                        text = { Text(penerbit.namaPenerbit) }
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = penulisExpanded,
            onExpandedChange = {penulisExpanded = !penulisExpanded}
        ) {
            OutlinedTextField(
                value = penulisList.find { it.idPenulis == bukuUiEvent.idPenulis }?.namaPenulis ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Penulis") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = penulisExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(R.color.creme2),
                    unfocusedBorderColor = colorResource(R.color.creme2),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = colorResource(R.color.black),
                    containerColor = Color.White
                )
            )
            ExposedDropdownMenu(
                expanded = penulisExpanded,
                onDismissRequest = {penulisExpanded = false}
            ) {
                penulisList.forEach { penulis ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(bukuUiEvent.copy(idPenulis = penulis.idPenulis))
                            penulisExpanded = false
                        },
                        text = { Text(penulis.namaPenulis) }
                    )
                }
            }
        }
        if (enabled){
            Text(
                text = "isi Semua Data!",
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}