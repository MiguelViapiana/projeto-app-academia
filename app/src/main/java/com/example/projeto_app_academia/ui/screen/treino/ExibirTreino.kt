package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.projeto_app_academia.AcademiaRotas
import com.example.projeto_app_academia.TelasTreinos
import com.example.projeto_app_academia.data.model.Exercicio
import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.getColorMenu
import com.example.projeto_app_academia.ui.mvvm.ExercicioViewModel
import com.example.projeto_app_academia.ui.mvvm.TreinoViewModel
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar
import com.example.projeto_app_academia.ui.screen.util.formatarDataCriacao
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun ExibirTreino(
    drawerState: DrawerState,
    navCtrlDrawer: NavHostController,
    viewModel: TreinoViewModel,
    viewlmodelExercicio: ExercicioViewModel,
    treinoId: Int? = null
){

    Scaffold (
        topBar = {AcademiaTopBar(drawerState, navCtrlDrawer)},
        content = {paddingValues ->
            if (treinoId != null) {
                ConteudoPrincipalExibirTreino(paddingValues, viewModel,viewlmodelExercicio, treinoId, navCtrlDrawer)
            }
        }
    )
}

@Composable
private fun ConteudoPrincipalExibirTreino(paddingValues: PaddingValues, viewModel: TreinoViewModel,viewlmodelExercicio: ExercicioViewModel, treinoId: Int, navCtrlDrawer: NavHostController){

    var coroutineScope = rememberCoroutineScope()
    var nome by remember { mutableStateOf("") }
    var treino: Treino? by remember { mutableStateOf(null) }
    var dataDeCriacao by remember { mutableStateOf("") }
    val exerciciosDb by viewlmodelExercicio.exercicios.collectAsState()

    LaunchedEffect(treinoId) {
        coroutineScope.launch {
            treino = viewModel.buscarTreinoPorId(treinoId)
            treino?.let {
                nome = it.nome
                dataDeCriacao = formatarDataCriacao(it.dataDeCriacao)

            }
        }
    }
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        IconButton(
            onClick = {
                navCtrlDrawer.navigate(TelasTreinos.TELA_LISTAR_TREINO)
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

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0x63275367)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp)

        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Nome: $nome",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color(0x8B000000),
                            offset = Offset(10f, 4f),
                            blurRadius = 5f
                        )
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Data de Criação: $dataDeCriacao",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                exerciciosDb.filter { it.treinoId == treinoId }.forEach { exercicio ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF275367)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Exercício: ${exercicio.nome}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Séries: ${exercicio.series} | Repetições: ${exercicio.repeticoes}",
                                    fontSize = 14.sp,
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            IconButton(
                                onClick = {
                                    exercicio.id?.let { viewlmodelExercicio.removerExercicioDoTreino(it) }
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Excluir treino",
                                    tint = Color(0xFFFFFFFF)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Row (
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            navCtrlDrawer.navigate("inserir_exercicio_categoria/${treino?.id}")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0x63275367)
                        )

                    ) {
                        Text("Adicionar Excercício",
                            fontSize = 20.sp,
                            color = Color.White)
                    }
                }
            }
        }
    }
}


