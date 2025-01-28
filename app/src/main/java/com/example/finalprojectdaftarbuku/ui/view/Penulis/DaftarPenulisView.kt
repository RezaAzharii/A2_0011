package com.example.finalprojectdaftarbuku.ui.view.Penulis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
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
import com.example.finalprojectdaftarbuku.model.Penulis
import com.example.finalprojectdaftarbuku.ui.customwidget.BottomBar
import com.example.finalprojectdaftarbuku.ui.customwidget.TopMainBar
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM.DaftarPenulisUiState
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM.DaftarPenulisViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarPenulis(
    navigateToDftrKategori: () -> Unit,
    navigateToDftrHome: () -> Unit,
    navigateToDftrStatus: () -> Unit,
    navigateToDftrPenerbit: () -> Unit,
    NavigateToEntryPenulis: () -> Unit,
    onEditClick: (Int) -> Unit = {},
    onDetailClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: DaftarPenulisViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        topBar = {
            TopMainBar(
                judul = "Authors",
                initialselected = 2,
                navigateToDftrHome = navigateToDftrHome,
                navigateToDftrKategori = navigateToDftrKategori,
                navigateToDftrPenerbit = navigateToDftrPenerbit,
                navigateToDftrPenulis = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = NavigateToEntryPenulis,
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
                navigateToDftrHome = navigateToDftrHome
            )
        }
    ){innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.creme2))
                .padding(innerPadding)
        ){
            PenulisStatus(
                pnsUiState = viewModel.pnsUiState,
                retryAction = { viewModel.getPenulis() },
                onDetailClick = onDetailClick,
                onEditClick = onEditClick,
                onDeleteClick = {
                    viewModel.deletePenulis(it.idPenulis)
                    viewModel.getPenulis()
                }
            )
        }
    }
}

@Composable
fun PenulisStatus(
    pnsUiState: DaftarPenulisUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Penulis) -> Unit = {},
){
    when(pnsUiState){
        is DaftarPenulisUiState.Loading -> OnLoading(
            modifier = modifier.fillMaxSize()
        )

        is DaftarPenulisUiState.Success ->
            if(pnsUiState.dataPns.isEmpty()){
                return Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Tidak ada Data Kategori")
                }
            }else{
                PenulisLayout(
                    penulis = pnsUiState.dataPns,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idPenulis)
                    },
                    onEditClick = {
                        onEditClick(it.idPenulis)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is DaftarPenulisUiState.Error -> OnError(
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
fun PenulisLayout(
    penulis: List<Penulis>,
    modifier: Modifier,
    onEditClick: (Penulis) -> Unit = {},
    onDetailClick: (Penulis) -> Unit = {},
    onDeleteClick: (Penulis) -> Unit = {}
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(penulis){pns ->
            CardPenulis(
                penulis = pns,
                modifier = Modifier
                    .fillMaxWidth(),
                onDetailClick = {
                    onDetailClick(pns)
                },
                onEditClick = {
                    onEditClick(pns)
                },
                onDeleteClick = {
                    onDeleteClick(pns)
                }
            )
        }
    }
}

@Composable
fun CardPenulis(
    penulis: Penulis,
    modifier: Modifier = Modifier,
    onDeleteClick: (Penulis) -> Unit = {},
    onEditClick: (Penulis) -> Unit = {},
    onDetailClick: (Penulis) -> Unit = {}
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    Card (
        modifier = modifier,
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
                text = penulis.namaPenulis,
                style = MaterialTheme.typography.titleLarge
            )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(onClick = {
                    onDetailClick(penulis)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_remove_red_eye_24),
                        contentDescription = null
                    )
                }
                IconButton(onClick = {
                    onEditClick(penulis)
                }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null
                    )
                }
                IconButton(onClick = {deleteConfirmationRequired = true}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
        }
    }
    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDeleteClick(penulis)
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
){
    AlertDialog(onDismissRequest = {},
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}