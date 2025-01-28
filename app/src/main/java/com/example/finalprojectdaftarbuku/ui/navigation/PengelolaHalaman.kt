package com.example.finalprojectdaftarbuku.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalprojectdaftarbuku.ui.view.Buku.DetailBukuScreen
import com.example.finalprojectdaftarbuku.ui.view.Buku.EditBukuView
import com.example.finalprojectdaftarbuku.ui.view.Buku.EntryBukuScreen
import com.example.finalprojectdaftarbuku.ui.view.Buku.HomeScreen
import com.example.finalprojectdaftarbuku.ui.view.Buku.StatusScreen
import com.example.finalprojectdaftarbuku.ui.view.Kategori.DaftarKategori
import com.example.finalprojectdaftarbuku.ui.view.Kategori.DetailKtgScreen
import com.example.finalprojectdaftarbuku.ui.view.Kategori.EditKategoriView
import com.example.finalprojectdaftarbuku.ui.view.Kategori.TambahKategoriScreen
import com.example.finalprojectdaftarbuku.ui.view.Penerbit.DaftarPenerbit
import com.example.finalprojectdaftarbuku.ui.view.Penerbit.DetailPnbScreen
import com.example.finalprojectdaftarbuku.ui.view.Penerbit.EditPenerbitView
import com.example.finalprojectdaftarbuku.ui.view.Penerbit.TambahPenerbitScreen
import com.example.finalprojectdaftarbuku.ui.view.Penulis.DaftarPenulis
import com.example.finalprojectdaftarbuku.ui.view.Penulis.DetailPnsScreen
import com.example.finalprojectdaftarbuku.ui.view.Penulis.EditPenulisView
import com.example.finalprojectdaftarbuku.ui.view.Penulis.TambahPenulisScreen

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        //Buku
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinsaiInsertBuku.route) },
                navigateToDftrStatus = { navController.navigate(DestinasiStatus.route) },
                navigateToDftrKategori = { navController.navigate(DestinasiDaftarKategori.route) },
                navigateToDftrPenerbit = { navController.navigate(DestinasiDaftarPenerbit.route) },
                navigateToDftrPenulis = { navController.navigate(DestinasiDaftarPenulis.route) },
                navigateDetailBku = { idBuku ->
                    navController.navigate("${DestinasiDetailBuku.route}/$idBuku")
                },
                navigateEditBku = { idBku ->
                    navController.navigate("${DestinasiUpdateBuku.route}/$idBku")
                }
            )
        }
        composable(DestinasiStatus.route){
            StatusScreen(
                navigateToHome = {navController.navigate(DestinasiHome.route)},
                navigateToDftrKategori = {navController.navigate(DestinasiDaftarKategori.route)},
                navigateToDftrPenerbit = {navController.navigate(DestinasiDaftarPenerbit.route)},
                navigateToDftrPenulis = {navController.navigate(DestinasiDaftarPenulis.route)},
                navigateDetailBku = {idBuku->
                    navController.navigate("${DestinasiDetailBuku.route}/$idBuku")
                },
                navigateEditBku = {idBku ->
                    navController.navigate("${DestinasiUpdateBuku.route}/$idBku")
                }
            )
        }
        composable(DestinsaiInsertBuku.route) {
            EntryBukuScreen(
                NavigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            DestinasiDetailBuku.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBuku.IDB){
                    type = NavType.IntType
                }
            )
        ){
            val idb = it.arguments?.getInt(DestinasiUpdateBuku.IDB)
            idb?.let {
                DetailBukuScreen(
                    navigateToItemUpdate = {
                        navController.navigate("${DestinasiUpdateBuku.route}/$idb")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiHome.route){
                            popUpTo(DestinasiHome.route){
                                inclusive = true
                            }
                        }
                    },
                    navigateToKategori = {
                        navController.navigate(DestinasiDaftarKategori.route)
                    }
                )
            }
        }
        composable(
            DestinasiUpdateBuku.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateBuku.IDB){
                    type = NavType.IntType
                }
            )
        ){
            val idb = it.arguments?.getInt(DestinasiUpdateBuku.IDB)
            idb?.let {
                EditBukuView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigate ={
                        navController.navigate(DestinasiHome.route)
                    }
                )
            }
        }

        //Kategori
        composable(DestinasiDaftarKategori.route){
            DaftarKategori(
                navigateToDftrStatus = {navController.navigate(DestinasiStatus.route)},
                navigateToDftrPenerbit = {navController.navigate(DestinasiDaftarPenerbit.route)},
                navigateToDftrPenulis = {navController.navigate(DestinasiDaftarPenulis.route)},
                NavigateToEntryKategori = {navController.navigate(DestinasiTambahKategori.route)},
                NavigateEditClick =  {idKategori ->
                    navController.navigate("${DestinasiUpdateKategori.route}/$idKategori")
                },
                onDetailClick = {idKategori ->
                    navController.navigate("${DestinasiDetailKategori.route}/$idKategori")
                },
                navigateToHome = {navController.navigate(DestinasiHome.route)}
            )
        }
        composable(DestinasiTambahKategori.route){
            TambahKategoriScreen(
                NavigateBack = {
                    navController.navigate(DestinasiDaftarKategori.route){
                        popUpTo(DestinasiDaftarKategori.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            DestinasiDetailKategori.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailKategori.IDK){
                    type = NavType.IntType
                }
            )
        ){
            val idk = it.arguments?.getInt(DestinasiUpdateKategori.IDK)
            idk?.let {
                DetailKtgScreen(
                    navigateToItemUpdate = {
                        navController.navigate("${DestinasiUpdateKategori.route}/$idk")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiDaftarKategori.route) {
                            popUpTo(DestinasiDaftarKategori.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        composable(
            DestinasiUpdateKategori.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDaftarKategori.IDK){
                    type = NavType.IntType
                }
            )
        ){
            val idk = it.arguments?.getInt(DestinasiUpdateKategori.IDK)
            idk?.let {
                EditKategoriView(
                    NavigateBack = { navController.popBackStack() },
                    onNavigate ={
                        navController.navigate(DestinasiDaftarKategori.route)
                    }
                )
            }
        }

        //Penerbit
        composable(DestinasiDaftarPenerbit.route){
            DaftarPenerbit(
                NavigateToEntryPenerbit = {navController.navigate(DestinasiTambahPenerbit.route)},
                onEditClick = {idPernebit ->
                    navController.navigate("${DestinasiUpdatePenerbit.route}/$idPernebit")
                },
                onDetailClick = {idPenerbit ->
                    navController.navigate("${DestinasiDetailPenerbit.route}/$idPenerbit")
                },
                navigateToDftrHome = {navController.navigate(DestinasiHome.route)},
                navigateToDftrKategori = {navController.navigate(DestinasiDaftarKategori.route)},
                navigateToDftrPenulis = {navController.navigate(DestinasiDaftarPenulis.route)},
                navigateToDftrStatus = {navController.navigate(DestinasiStatus.route)}
            )
        }
        composable(DestinasiTambahPenerbit.route){
            TambahPenerbitScreen(
                NavigateBack = {
                    navController.navigate(DestinasiDaftarPenerbit.route){
                        popUpTo(DestinasiDaftarPenerbit.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

    }
}