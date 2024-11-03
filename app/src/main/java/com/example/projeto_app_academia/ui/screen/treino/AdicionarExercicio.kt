package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projeto_app_academia.AcademiaRotas
import com.example.projeto_app_academia.data.model.Categoria
import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.ui.mvvm.CategoriaViewModel
import com.example.projeto_app_academia.ui.mvvm.ExercicioViewModel
import com.example.projeto_app_academia.ui.mvvm.TreinoViewModel
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar
import kotlinx.coroutines.launch


@Composable
fun AdicionarExercicioScreen(
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
        Text("Selecione uma Categoria")
        LazyColumn {

            items(categorias) { categoria ->
                Text(
                    text = categoria.nome,
                    modifier = Modifier.clickable {

                    }
                )
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

