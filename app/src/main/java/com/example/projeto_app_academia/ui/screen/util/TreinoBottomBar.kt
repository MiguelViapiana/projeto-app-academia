package com.example.projeto_app_academia.ui.screen.util

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projeto_app_academia.TelasTreinos

@Composable
fun TreinoBottomBar(navController: NavController, currentScreen: MutableState<String>) {


    NavigationBar(
        containerColor = Color(0xFF326886)
    ) {
        NavigationBarItem(selected = currentScreen.value == TelasTreinos.TELA_LISTAR_TREINO,
            onClick = {
                currentScreen.value = TelasTreinos.TELA_LISTAR_TREINO
                navController.navigate(TelasTreinos.TELA_LISTAR_TREINO) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Lista",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
            },
            label = { Text("Listar Treinos", color = Color.White) }
        )
        NavigationBarItem(selected = currentScreen.value == TelasTreinos.TELA_ADICIONAR_TREINO,
            onClick = {
                currentScreen.value = TelasTreinos.TELA_ADICIONAR_TREINO
                navController.navigate(TelasTreinos.TELA_ADICIONAR_TREINO) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
            },
            label = { Text("Adicionar Treino", color = Color.White) }
        )
    }
}
