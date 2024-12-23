package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.projeto_app_academia.data.model.Exercicio
import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.ui.mvvm.CategoriaViewModel
import com.example.projeto_app_academia.ui.mvvm.ExercicioViewModel
import com.example.projeto_app_academia.ui.mvvm.TreinoViewModel
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar
import kotlinx.coroutines.launch
import java.util.Date


@Composable
fun AdicionarExercicioNovoScreen(
    drawerState: DrawerState,
    navCtrlDrawer: NavController,
    viewModelTreino: TreinoViewModel,
    viewModelCategoria: CategoriaViewModel,
    viewModelExercicio: ExercicioViewModel,
    treinoId: Int? = null,
    categoriaId: Int? = null,
    exercicioNome: String? = null
    )
{

    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navCtrlDrawer) },
        content = { paddingValues ->  ConteudoPrincipalAdicionarNovo(paddingValues, viewModelTreino, viewModelCategoria, viewModelExercicio, treinoId,categoriaId, exercicioNome, navCtrlDrawer) },
      //  bottomBar = {TreinoBottomBar(navController = navCtrlBottomNav, currentScreen)}
    )
}

@Composable
private fun ConteudoPrincipalAdicionarNovo(
    paddingValues: PaddingValues,
    viewModel: TreinoViewModel,
    viewModelCategoria: CategoriaViewModel,
    viewModelExercicio: ExercicioViewModel,
    treinoId: Int?,
    categoriaId: Int?,
    exercicioNome: String? = null,
    navCtrlDrawer: NavController
) {

    val coroutineScope = rememberCoroutineScope()

    var exercicio: Exercicio? by remember { mutableStateOf(null) }
    var nome by remember { mutableStateOf("") }
    var series by remember { mutableIntStateOf(0) }
    var repeticoes by remember { mutableIntStateOf(0) }

    var treino: Treino? by remember { mutableStateOf(null) }

    LaunchedEffect(exercicioNome) {
        coroutineScope.launch {
            if (exercicioNome != null) {
                nome = exercicioNome
            }
        }
    }

    Row(modifier = Modifier.padding(paddingValues)) {
        IconButton(
            onClick = {
                navCtrlDrawer.navigate("inserir_exercicio/${treinoId}/${categoriaId}")
            },
            modifier = Modifier
                .size(58.dp)
                .padding(16.dp, 4.dp),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                tint = Color(0xFF275367),
                modifier = Modifier.size(40.dp)
            )
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
        Text(" Adicionar Exercicio á planilha", style = MaterialTheme.typography.titleLarge)

        //Nome
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        //Series
        OutlinedTextField(
            value = series.toString(),
            onValueChange = { series = it.toIntOrNull() ?: 0 },
            label = { Text("Séries") }
        )
        //Repetições
        OutlinedTextField(
            value = repeticoes.toString(),
            onValueChange = { repeticoes = it.toIntOrNull() ?: 0 },
            label = { Text("Repetições") }
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    val exercicioSalvar = categoriaId?.let {
                        Exercicio(
                            id = null,
                            nome = nome,
                            series = series,
                            repeticoes = repeticoes,
                            categoriaId = it,
                            treinoId = treinoId
                        )
                    }
                    if (exercicioSalvar != null && treinoId != null) {
                        viewModelExercicio.gravar(exercicioSalvar)

                    }
                    navCtrlDrawer.navigate("exibir_treino/${treinoId}")
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

