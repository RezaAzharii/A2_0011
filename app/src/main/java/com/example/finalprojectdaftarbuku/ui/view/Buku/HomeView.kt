package com.example.finalprojectdaftarbuku.ui.view.Buku

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import com.example.finalprojectdaftarbuku.model.Buku
import com.example.finalprojectdaftarbuku.ui.customwidget.BottomBar
import com.example.finalprojectdaftarbuku.ui.customwidget.TopMainBar
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.HomeUiState
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.HomeViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToDftrKategori: () -> Unit,
    navigateToDftrPenerbit: () -> Unit,
    navigateToDftrPenulis: () -> Unit,
    navigateToDftrStatus: () -> Unit,
    modifier: Modifier = Modifier,
    navigateDetailBku: (Int) -> Unit = {},
    navigateEditBku: (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    Scaffold (
        topBar = {
            TopMainBar(
                judul = "Book",
                navigateToDftrHome = {},
                navigateToDftrKategori = navigateToDftrKategori,
                navigateToDftrPenerbit = navigateToDftrPenerbit,
                navigateToDftrPenulis = navigateToDftrPenulis
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(6.dp),
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Buku"
                )
            }
        },
        bottomBar = {
            BottomBar(
                initialSelectedItem = 0,
                navigateToDftrStatus = navigateToDftrStatus,
                navigateToDftrHome = {},
            )
        }
    ){innerPadding ->
        Box(
            modifier = Modifier
                .background(color = colorResource(R.color.creme2))
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            HomeStatus(
                homeUiState = viewModel.bkuUiState,
                retryAction = { viewModel.getBuku() },
                onDetailClick = navigateDetailBku,
                onEditClick = navigateEditBku,
                onDeleteClick = {
                    viewModel.deleteBuku(it.idBuku)
                    viewModel.getBuku()
                },
            )
        }
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Buku) -> Unit
){
    when(homeUiState){
        is HomeUiState.Loading -> OnLoading(modifier = Modifier.fillMaxSize())

        is HomeUiState.Success ->
            if (homeUiState.buku.isEmpty()){
                return Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Tidak ada data Buku")
                }
            }else{
                BookLayout(
                    buku = homeUiState.buku,
                    modifier = Modifier
                        .fillMaxSize(),
                    onDetailClick = {
                        onDetailClick(it.idBuku)
                    },
                    onEditClick = {
                        onEditClick(it.idBuku)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
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
            painter = painterResource(id=R.drawable.ic_launcher_foreground),
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
fun BookLayout(
    buku: List<Buku>,
    modifier: Modifier = Modifier,
    onDetailClick:(Buku) -> Unit,
    onEditClick:(Buku) -> Unit = {},
    onDeleteClick:(Buku) -> Unit = {}
){
    LazyColumn (
        modifier = Modifier
            .fillMaxHeight(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){ items(buku){bku ->
            CardBook(
                buku = bku,
                modifier = Modifier,
                onDetailClick = {onDetailClick(bku)},
                onEditClick = {onEditClick(bku)},
                onDeleteClick = {onDeleteClick(bku)}
            )
        }
    }
}

@Composable
fun CardBook(
    buku: Buku,
    modifier: Modifier = Modifier,
    onDetailClick: (Buku) -> Unit,
    onEditClick:(Buku) -> Unit,
    onDeleteClick:(Buku) -> Unit,
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    Card (
        modifier = modifier
            .clickable { onDetailClick(buku) },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.crem))
    ){
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Text(
                    text = buku.namaBuku,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = buku.deskripsiBuku,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = buku.tanggalTerbit,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
            Column {
                Text(
                    text = buku.statusBuku,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(R.color.orange)
                )
                IconButton(onClick = {
                    onEditClick(buku)
                }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    deleteConfirmationRequired = true
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDeleteClick(buku)
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
        title = { Text("Delete Data")},
        text = { Text("Apakah anda yakin ingin menghapus data?")},
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
