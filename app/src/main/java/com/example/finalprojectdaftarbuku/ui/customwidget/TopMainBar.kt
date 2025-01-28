package com.example.finalprojectdaftarbuku.ui.customwidget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalprojectdaftarbuku.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMainBar(
    navigateToDftrHome: () -> Unit,
    navigateToDftrKategori: () -> Unit,
    navigateToDftrPenerbit: () -> Unit,
    navigateToDftrPenulis: () -> Unit,
    judul: String,
    initialselected: Int = 0,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    var selectedMenuItem by rememberSaveable { mutableStateOf(initialselected) }

    Box(
        modifier = modifier
            .height(133.dp)
            .background(
                color = colorResource(R.color.green),
                shape = RoundedCornerShape(
                    bottomEnd = 24.dp,
                    bottomStart = 24.dp
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = judul,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.White),
                modifier = Modifier.clickable { navigateToDftrHome() }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MenuText(
                    text = "Categories",
                    isSelected = selectedMenuItem == 1,
                    onClick = {
                        selectedMenuItem = 1
                        navigateToDftrKategori()
                    }
                )
                MenuText(
                    text = "Authors",
                    isSelected = selectedMenuItem == 2,
                    onClick = {
                        selectedMenuItem = 2
                        navigateToDftrPenulis()
                    }
                )
                MenuText(
                    text = "Publisher",
                    isSelected = selectedMenuItem == 3,
                    onClick = {
                        selectedMenuItem = 3
                        navigateToDftrPenerbit()
                    }
                )
            }
        }
    }
}

@Composable
fun MenuText(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = if (isSelected) colorResource(R.color.White) else Color.Gray,
        modifier = Modifier.clickable { onClick() }
    )
}