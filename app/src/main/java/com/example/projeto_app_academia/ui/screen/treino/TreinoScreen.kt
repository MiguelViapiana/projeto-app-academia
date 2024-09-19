package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar

object TelasTreinos{
    val TELA_LISTAR_TREINO = "listar_treino"
    val TELA_ADICIONAR_TREINO = "adicionar_treino"
}

@Composable
fun TreinoScreen(drawerState: DrawerState, navController: NavController) {

    val navController = rememberNavController()

    val currentScreen = remember { mutableStateOf(TelasTreinos.TELA_LISTAR_TREINO) }


    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navController) },
        content = { padding ->

            NavHost(navController = navController, startDestination = TelasTreinos.TELA_LISTAR_TREINO)
            {
                composable(TelasTreinos.TELA_LISTAR_TREINO) {
                    ListarTreinoScreen(padding)
                }
                composable(TelasTreinos.TELA_ADICIONAR_TREINO) {
                    AdicionarTreinoScreen(padding)
                }
            }
        },
        bottomBar = { BottomAppBarMinima(navController, currentScreen) }

    )
}

@Composable
fun BottomAppBarMinima(navController: NavController, currentScreen: MutableState<String>) {
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

@Composable
private fun ConteudoPrincipal(padding: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "SignUpScreen", Modifier.padding(padding),
            fontSize = 50.sp
        )
    }
}

//@Composable
//private fun BottomAppBarMinima(navController: NavController) {
//
//
//    NavigationBar(
//        containerColor = Color(0xD803A9F4)
//    ) {
//        NavigationBarItem(selected = false,
//            onClick = {
//                navController.navigate(TelaUm.TELA_UM_A_ROUTE)
//            }, icon = {
//                Icon(
//                    imageVector = Icons.Default.CheckCircle,
//                    contentDescription = "A",
//                    modifier = Modifier.size(40.dp)
//                )
//            },
//            label = {Text("Tela A")}
//        )
//
//        NavigationBarItem(selected = false,
//            onClick = {
//                navController.navigate(TelaUm.TELA_UM_B_ROUTE)
//            }, icon = {
//                Icon(
//                    imageVector = Icons.Default.DateRange,
//                    contentDescription = "B",
//                    modifier = Modifier.size(40.dp)
//                )
//            },
//            label = {Text("Tela B")}
//        )
//
//        NavigationBarItem(selected = false,
//            onClick = {
//                navController.navigate(TelaUm.TELA_UM_C_ROUTE)
//            }, icon = {
//                Icon(
//                    imageVector = Icons.Default.Email,
//                    contentDescription = "C",
//                    modifier = Modifier.size(40.dp)
//                )
//            },
//            label = {Text("Tela C")}
//        )
//    }
//}


