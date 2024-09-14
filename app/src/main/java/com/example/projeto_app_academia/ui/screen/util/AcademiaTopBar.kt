package com.example.projeto_app_academia.ui.screen.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projeto_app_academia.AcademiaRotas
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
 fun AcademiaTopBar(drawerState: DrawerState, navController: NavController) {

    val escopo = rememberCoroutineScope()

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                escopo.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        },

        title = {
            Text(
                text = "AcademiaApp",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(AcademiaRotas.TELA_lOGIN)
            }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Login",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color(0xFF275367))
    )
}