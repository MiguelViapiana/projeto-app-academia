package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projeto_app_academia.AcademiaRotas
import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.ui.mvvm.TreinoViewModel
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar
import kotlinx.coroutines.launch


@Composable
fun AdicionarEditarTreinoScreen(
    drawerState: DrawerState,
    navCtrlDrawer: NavController,
    viewModel: TreinoViewModel,
    treinoId: Int? = null
    )
{

    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navCtrlDrawer) },
        content = { paddingValues ->  ConteudoPrincipalAdicionar(paddingValues, viewModel, treinoId, navCtrlDrawer) },
      //  bottomBar = {TreinoBottomBar(navController = navCtrlBottomNav, currentScreen)}
    )
}

@Composable
private fun ConteudoPrincipalAdicionar(
    paddingValues: PaddingValues,
    viewModel: TreinoViewModel,
    treinoId: Int?,
    navCtrlDrawer: NavController
) {

    val coroutineScope = rememberCoroutineScope()
    var nome by remember { mutableStateOf("") }
    var treino: Treino? by remember { mutableStateOf(null) }

    LaunchedEffect(treinoId) {
        coroutineScope.launch {
            if(treinoId != null){
                treino = viewModel.buscarTreinoPorId(treinoId);
                treino?.let {
                    nome = it.nome
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(" Adicionar Treino", style = MaterialTheme.typography.titleLarge)

        //Nome
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") }
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    val treinoSalvar = Treino(
                        id = treinoId,
                        nome = nome,
                    )
                    viewModel.gravar(treinoSalvar)
                    navCtrlDrawer.navigate(AcademiaRotas.TELA_LISTAR_TREINO)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF275367) // Azul específico
            )
        ) {
            Text(text = "Salvar", fontSize = 30.sp)
        }
    }
}

