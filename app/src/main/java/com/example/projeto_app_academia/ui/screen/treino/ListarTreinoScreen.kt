package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar
import com.example.projeto_app_academia.ui.screen.util.TreinoBottomBar


@Composable
fun ListarTreinoScreen(drawerState: DrawerState, navCtrlBottomNav: NavController, currentScreen: MutableState<String>){

    val navListarTreino = rememberNavController()

    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navListarTreino) },
        content = {paddingValues -> ConteudoPrincipalListar(paddingValues) },
        bottomBar = {TreinoBottomBar(navCtrlBottomNav, currentScreen)}
    )
}

@Composable
private fun ConteudoPrincipalListar(padding: PaddingValues) {
    var treinos = mutableListOf(
        Treino(
            titulo = "Treino A"
        ),
        Treino(
            titulo = "Treino B"
        )
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(treinos) { treino ->
            Row {
                Column {
                    Text(
                        text = treino.titulo,
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp
                    )
                }
            }
        }
    }
}

data class Treino(
    var titulo: String,
    var concluido: Boolean = false,
    var id: Int? = null
)
