package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.projeto_app_academia.data.model.Exercicio
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
    treinoId: Int? = null,
    categoriaId: Int? = null
)
{
    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navCtrlDrawer) },
        content = { paddingValues ->  ConteudoPrincipalAdicionar(paddingValues, viewModelTreino, viewModelCategoria, viewModelExercicio, treinoId,categoriaId, navCtrlDrawer) }
    )
}

@Composable
private fun ConteudoPrincipalAdicionar(
    paddingValues: PaddingValues,
    viewModel: TreinoViewModel,
    viewModelCategoria: CategoriaViewModel,
    viewModelExercicio: ExercicioViewModel,
    treinoId: Int?,
    categoriaId: Int?,
    navCtrlDrawer: NavController
) {

    val coroutineScope = rememberCoroutineScope()
    val categorias by viewModelCategoria.categorias.collectAsState()
    val exercicios by viewModelExercicio.exercicios.collectAsState()
    var treino: Treino? by remember { mutableStateOf(null) }


    Row(modifier = Modifier.padding(paddingValues)) {
        IconButton(
            onClick = {
                navCtrlDrawer.navigate("inserir_exercicio_categoria/${treinoId}")
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
        Text(
            "Selecione um Exercício",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF275367),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )


        LazyColumn {
            val nomesUnicos = exercicios
                .filter { it.categoriaId == categoriaId } // Filtra pela categoria
                .map { it.nome } // Mapeia para os nomes
                .toSet()

            items(nomesUnicos.toList()) { nomeExercicio ->
                val exercicio = exercicios.first { it.nome == nomeExercicio }

                if(exercicio.categoriaId == categoriaId){
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0x63275367)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 8.dp)
                            .clickable {
                                navCtrlDrawer.navigate("inserir_exercicio_existente/${treinoId}/${categoriaId}/${exercicio.nome}")
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
                                    text = exercicio.nome,
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
        }
        Row {
            Button(
                onClick = {
                    navCtrlDrawer.navigate("inserir_exercicio_novo/${treinoId}/${categoriaId}")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0x63275367)
                )
            ) {
                Text("Adicionar novo excercício")
            }
        }
    }
}