package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projeto_app_academia.TelasTreinos

@Composable
fun TreinoNavHost(drawerState: DrawerState, navCtrlBottomNav: NavHostController) {

    val currentScreen = remember { mutableStateOf(TelasTreinos.TELA_ADICIONAR_TREINO) }

    NavHost(
        navController = navCtrlBottomNav,
        startDestination = TelasTreinos.TELA_LISTAR_TREINO
    ) {
        composable(TelasTreinos.TELA_LISTAR_TREINO) {
            ListarTreinoScreen(drawerState, navCtrlBottomNav, currentScreen)
        }
        composable(TelasTreinos.TELA_ADICIONAR_TREINO) {
            AdicionarTreinoScreen(drawerState, navCtrlBottomNav, currentScreen)
        }
    }

}

@Composable
private fun ConteudoPrincipalListar(padding: PaddingValues) {
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
