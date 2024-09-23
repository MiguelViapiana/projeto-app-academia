package com.example.projeto_app_academia.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar

@Composable
fun HistoricoScreen(drawerState: DrawerState, navController: NavController){
    Scaffold (
        topBar = { AcademiaTopBar(drawerState, navController)},
        content = {padding ->  ConteudoPrincipal(padding) }
    )
}

@Composable
private fun ConteudoPrincipal(padding: PaddingValues){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Historico dos treinos",
             modifier = Modifier.padding(padding)
        )
    }
}
