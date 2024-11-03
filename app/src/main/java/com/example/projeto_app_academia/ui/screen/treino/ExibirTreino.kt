package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.ui.mvvm.TreinoViewModel
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun ExibirTreino(
    drawerState: DrawerState,
    navController: NavController,
    viewModel: TreinoViewModel,
    treinoId: Int? = null
){

    Scaffold (
        topBar = {AcademiaTopBar(drawerState, navController)},
        content = {paddingValues ->
            if (treinoId != null) {
                ConteudoPrincipalExibirTreino(paddingValues, viewModel, treinoId)
            }
        }
    )
}

@Composable
private fun ConteudoPrincipalExibirTreino(paddingValues: PaddingValues, viewModel: TreinoViewModel, treinoId: Int){

    var coroutineScope = rememberCoroutineScope()
    var nome by remember { mutableStateOf("") }
    var treino: Treino? by remember { mutableStateOf(null) }

    LaunchedEffect(treinoId) {
        coroutineScope.launch {
            treino = viewModel.buscarTreinoPorId(treinoId)
            treino?.let {
                nome = it.nome
            }
        }
    }
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Text("Nome: $nome")
    }


}