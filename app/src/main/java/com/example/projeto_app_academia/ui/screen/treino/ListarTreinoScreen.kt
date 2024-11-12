package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.ui.mvvm.TreinoViewModel
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar
import com.example.projeto_app_academia.ui.screen.util.TreinoBottomBar
import com.example.projeto_app_academia.ui.screen.util.formatarDataCriacao
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ListarTreinoScreen(
    drawerState: DrawerState,
    navCtrlDrawer: NavHostController,
    viewModel: TreinoViewModel,
    usuarioId: Int
)
{
    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navCtrlDrawer) },
        content = {paddingValues -> ConteudoPrincipalListar(paddingValues, viewModel, navCtrlDrawer, usuarioId)},
       // bottomBar = {TreinoBottomBar(navCtrlBottomNav, currentScreen)}
    )
}

@Composable
private fun ConteudoPrincipalListar(padding: PaddingValues, viewModel: TreinoViewModel, navCtrlDrawer: NavHostController, usuarioId: Int) {

    var coroutineScope = rememberCoroutineScope()
    val treinos by viewModel.treinos.collectAsState()
    var showDialog by remember { mutableStateOf<Treino?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {
        items(treinos) { treino ->
            if (treino.usuarioId == usuarioId){

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0x63275367)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically // Alinha o conteúdo verticalmente ao centro
                    ) {
                        Column(
                            modifier = Modifier.weight(3f),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = treino.nome,
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
                                text = "Data de criação: " + formatarDataCriacao(treino.dataDeCriacao),
                                fontSize = 16.sp,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row {
                                IconButton(
                                    onClick = {
                                        showDialog = treino
                                    },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Excluir treino",
                                        tint = Color(0xFF275367)
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        navCtrlDrawer.navigate("editar_treino/${treino.id}")
                                    },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Create,
                                        contentDescription = "Editar treino",
                                        tint = Color(0xFF275367)
                                    )
                                }
                            }

                        }

                        // Espaço flexível entre o texto e o ícone
                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            onClick = {
                                navCtrlDrawer.navigate("exibir_treino/${treino.id}")
                            },
                            modifier = Modifier
                                .size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Opções",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    showDialog?.let { treino ->
        AlertDialog(
            onDismissRequest = { showDialog = null },
            title = {
                Text(text = "Confirmar Exclusão")
            },
            text = {
                Text(text = "Tem certeza de que deseja excluir o treino \"${treino.nome}\"?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.excluir(treino)
                        showDialog = null
                    }
                ) {
                    Text("Excluir", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}