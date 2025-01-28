package com.example.finalprojectdaftarbuku.ui.view.Penerbit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectdaftarbuku.R
import com.example.finalprojectdaftarbuku.model.Penerbit
import com.example.finalprojectdaftarbuku.ui.customwidget.BottomBar
import com.example.finalprojectdaftarbuku.ui.customwidget.TopMainBar
import com.example.finalprojectdaftarbuku.ui.view.Kategori.KategoriStatus
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.DaftarKategoriViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.DaftarPenerbitUiState
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.DaftarPenerbitViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarPenerbit(
    NavigateToEntryPenerbit: () -> Unit,
    navigateToDftrKategori: () -> Unit,
    navigateToDftrStatus: () -> Unit,
    navigateToDftrHome: () -> Unit,
    navigateToDftrPenulis: () -> Unit,
    onEditClick: (Int) -> Unit = {},
    onDetailClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: DaftarPenerbitViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        topBar = {
            TopMainBar(
                judul = "Publisher",
                initialselected = 3,
                navigateToDftrHome = navigateToDftrHome,
                navigateToDftrKategori = navigateToDftrKategori,
                navigateToDftrPenerbit = {},
                navigateToDftrPenulis = navigateToDftrPenulis
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = NavigateToEntryPenerbit,
                shape = MaterialTheme.shapes.medium,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color.White,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Buku"
                )
            }
        },
        bottomBar = {
            BottomBar(
                initialSelectedItem = -1,
                navigateToDftrStatus = navigateToDftrStatus,
                navigateToDftrHome = navigateToDftrHome,
            )
        }
    ){innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.creme2))
                .padding(innerPadding)
        ){
            PenerbitStatus(
                pnbUiState = viewModel.pnbUiState,
                retryAction = { viewModel.getPenerbit() },
                onDetailClick = onDetailClick,
                onEditClick = onEditClick,
                onDeleteClick = {
                    viewModel.deletePenerbit(it.idPenerbit)
                    viewModel.getPenerbit()
                }
            )
        }
    }
}

@Composable
fun PenerbitStatus(
    pnbUiState: DaftarPenerbitUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Penerbit) -> Unit = {},
){
    when(pnbUiState){
        is DaftarPenerbitUiState.Loading -> OnLoading(
            modifier = modifier.fillMaxSize()
        )

        is DaftarPenerbitUiState.Success ->
            if(pnbUiState.dataKtg.isEmpty()){
                return Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Tidak ada Data Kategori")
                }
            }else{
                PenerbitLayout(
                    penerbit = pnbUiState.dataKtg,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idPenerbit)
                    },
                    onEditClick = {
                        onEditClick(it.idPenerbit)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is DaftarPenerbitUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun OnLoading(
    modifier: Modifier
){
    Image(
        modifier =  modifier.size(200.dp),
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(
    retryAction: () -> Unit,
    modifier: Modifier
){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id= R.drawable.ic_launcher_foreground),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun PenerbitLayout(
    penerbit: List<Penerbit>,
    modifier: Modifier ,
    onEditClick: (Penerbit) -> Unit = {},
    onDetailClick: (Penerbit) -> Unit = {},
    onDeleteClick: (Penerbit) -> Unit = {}
){
    LazyColumn (
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(penerbit){pnb ->
            CardPenerbit(
                penerbit = pnb,
                modifier = Modifier
                    .fillMaxWidth(),
                onDetailClick = {
                    onDetailClick(pnb)
                },
                onEditClick = {
                    onEditClick(pnb)
                },
                onDeleteClick = {
                    onDeleteClick(pnb)
                }
            )
        }
    }
}

@Composable
fun CardPenerbit(
    penerbit: Penerbit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Penerbit) -> Unit = {},
    onEditClick: (Int) -> Unit = {},
    onDetailClick: (Penerbit) -> Unit = {}
){
    var actionDialogRequired by rememberSaveable { mutableStateOf(false) }
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    Card (
        modifier = modifier
            .clickable { actionDialogRequired = true  },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.crem))
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = penerbit.namaPenerbit,
                style = MaterialTheme.typography.titleLarge
            )
            IconButton(onClick = {
                onDetailClick(penerbit)
            }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_remove_red_eye_24),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }
        }
    }
    if (actionDialogRequired) {
        ActionDialog(
            onEditClick = {
                actionDialogRequired = false
                onEditClick(penerbit.idPenerbit)
            },
            onDeleteClick = {
                actionDialogRequired = false
                deleteConfirmationRequired = true
            },
            onDismiss = { actionDialogRequired = false },
            modifier = Modifier.padding(8.dp)
        )
    }
    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDeleteClick(penerbit)
            },
            onDeleteCancel = {
                deleteConfirmationRequired = false
            }, modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun ActionDialog(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Pilih") },
        text = { Text("Apa yang ingin Anda lakukan untuk penerbit ini?") },
        modifier = modifier,
        confirmButton = {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = onDeleteClick) {
                    Text("Hapus")
                }
                TextButton(onClick = onEditClick) {
                    Text("Edit")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        },

    )
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