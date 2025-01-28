package com.example.finalprojectdaftarbuku.ui.customwidget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalprojectdaftarbuku.R

//@Preview(showBackground = true)
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navigateToDftrHome: () -> Unit,
    navigateToDftrStatus: () -> Unit,
    initialSelectedItem: Int = 0
) {
    var selectedItem by rememberSaveable { mutableStateOf(initialSelectedItem) }

    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = colorResource(R.color.green),
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = {
                selectedItem = 0
                navigateToDftrHome()
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_filled_24),
                    contentDescription = "Home",
                    tint = if (selectedItem == 0) Color.White else Color.Gray
                )
            },
            label = {
                Text(
                    text = "Home",
                    color = if (selectedItem == 0) Color.White else Color.Gray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = colorResource(R.color.green)
            )
        )

        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = {
                selectedItem = 1
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "Search",
                    tint = if (selectedItem == 1) Color.White else Color.Gray
                )
            },
            label = {
                Text(
                    text = "Search",
                    color = if (selectedItem == 1) Color.White else Color.Gray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = colorResource(R.color.green)
            )
        )

        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = {
                selectedItem = 2
                navigateToDftrStatus()
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_library_books_24),
                    contentDescription = "Library",
                    tint = if (selectedItem == 2) Color.White else Color.Gray
                )
            },
            label = {
                Text(
                    text = "Status",
                    color = if (selectedItem == 2) Color.White else Color.Gray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = colorResource(R.color.green)
            ),
        )
    }
}