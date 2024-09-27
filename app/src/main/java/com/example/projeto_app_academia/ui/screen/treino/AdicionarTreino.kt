package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar
import com.example.projeto_app_academia.ui.screen.util.TreinoBottomBar


@Composable
fun AdicionarTreinoScreen(drawerState: DrawerState, navCtrlBottomNav: NavController, currentScreen: MutableState<String>){

    val navAdicionarTreino = rememberNavController()

    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navAdicionarTreino) },
        content = { paddingValues ->  ConteudoPrincipalAdicionar(paddingValues) },
        bottomBar = {TreinoBottomBar(navController = navCtrlBottomNav, currentScreen)}
    )
}

@Composable
private fun ConteudoPrincipalAdicionar(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(" Adicionar exercicio", style = MaterialTheme.typography.titleLarge)

        //Nome
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Nome") }
        )

        //Peso
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Peso") }
        )

        //Repetição
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Repetição") }
        )
    }
}

