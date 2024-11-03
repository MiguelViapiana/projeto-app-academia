package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.ui.mvvm.CategoriaViewModel
import com.example.projeto_app_academia.ui.mvvm.ExercicioViewModel
import com.example.projeto_app_academia.ui.mvvm.TreinoViewModel
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar


@Composable
fun AdicionarExercicioCategoriaScreen(
    drawerState: DrawerState,
    navCtrlDrawer: NavController,
    viewModelTreino: TreinoViewModel,
    viewModelCategoria: CategoriaViewModel,
    viewModelExercicio: ExercicioViewModel,
    treinoId: Int? = null
    )
{

    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navCtrlDrawer) },
        content = { paddingValues ->  ConteudoPrincipalAdicionar(paddingValues, viewModelTreino, viewModelCategoria, viewModelExercicio, treinoId, navCtrlDrawer) }
    )
}

@Composable
private fun ConteudoPrincipalAdicionar(
    paddingValues: PaddingValues,
    viewModel: TreinoViewModel,
    viewModelCategoria: CategoriaViewModel,
    viewModelExercicio: ExercicioViewModel,
    treinoId: Int?,
    navCtrlDrawer: NavController
) {

    val coroutineScope = rememberCoroutineScope()
    val categorias by viewModelCategoria.categorias.collectAsState()
    val exercicios by viewModelExercicio.exercicios.collectAsState()
    var treino: Treino? by remember { mutableStateOf(null) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text("Selecione uma Categoria",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF275367),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
        LazyColumn {

            items(categorias) { categoria ->

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0x63275367)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable{
                            navCtrlDrawer.navigate("inserir_exercicio/${treinoId}/${categoria.id}")
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(3f),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = categoria.nome,
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
                        }
                    }
                }
            }
        }
//        Button(
//            onClick = {
//                coroutineScope.launch {
//                    val categoriaSalvar01 = Categoria(
//                        id = null,
//                        nome = "Abdomên"
//                    )
//                    viewModelCategoria.gravar(categoriaSalvar01)
//                    val categoriaSalvar02 = Categoria(
//                        id = null,
//                        nome = "Costas"
//                    )
//                    viewModelCategoria.gravar(categoriaSalvar02)
//                    val categoriaSalvar03 = Categoria(
//                        id = null,
//                        nome = "Bíceps"
//                    )
//                    viewModelCategoria.gravar(categoriaSalvar03)
//                    val categoriaSalvar04 = Categoria(
//                        id = null,
//                        nome = "Peito"
//                    )
//                    viewModelCategoria.gravar(categoriaSalvar04)
//                    val categoriaSalvar05 = Categoria(
//                        id = null,
//                        nome = "Perna"
//                    )
//                    viewModelCategoria.gravar(categoriaSalvar05)
//                    val categoriaSalvar06 = Categoria(
//                        id = null,
//                        nome = "Ombro"
//                    )
//                    viewModelCategoria.gravar(categoriaSalvar06)
//                    val categoriaSalvar07 = Categoria(
//                        id = null,
//                        nome = "Tríceps"
//                    )
//
//                    viewModelCategoria.gravar(categoriaSalvar07)
//
//
//                }
//            },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF275367) // Azul específico
//            )
//        ) {
//            Text(text = "Salvar", fontSize = 30.sp)
//        }

//        //Nome
//        OutlinedTextField(
//            value = nome,
//            onValueChange = { nome = it },
//            label = { Text("Nome") }
//        )
//        Button(
//            onClick = {
//                coroutineScope.launch {
//                    val treinoSalvar = Treino(
//                        id = treinoId,
//                        nome = nome,
//                    )
//                    viewModel.gravar(treinoSalvar)
//                    navCtrlDrawer.navigate(AcademiaRotas.TELA_LISTAR_TREINO)
//                }
//            },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF275367) // Azul específico
//            )
//        ) {
//            Text(text = "Salvar", fontSize = 30.sp)
//        }
    }
}

