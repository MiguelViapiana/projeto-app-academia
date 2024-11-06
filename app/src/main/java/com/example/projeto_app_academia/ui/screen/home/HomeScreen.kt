package com.example.projeto_app_academia.ui.screen.home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import com.example.projeto_app_academia.AcademiaRotas
import com.example.projeto_app_academia.ui.mvvm.TreinoViewModel
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar
import com.example.projeto_app_academia.ui.screen.util.formatarDataCriacao


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen(drawerState: DrawerState, navCtrlDrawer: NavHostController, viewModel: TreinoViewModel) {
    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navCtrlDrawer) },
        content = {padding -> ConteudoPrincipal(padding,navCtrlDrawer, viewModel) }
    )
}

@Composable
private fun ConteudoPrincipal(padding: PaddingValues, navCtrlDrawer: NavHostController, viewModel: TreinoViewModel) {

    var coroutineScope = rememberCoroutineScope()
    val treinos by viewModel.treinos.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf5f5f5))
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Treinos Recentes",
            modifier = Modifier.padding(padding),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF275367),
            textAlign = TextAlign.Center
        )
        for(treino in treinos.takeLast(3)){
        //items(treinos){ treino ->

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0x63275367)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically // Alinha o conteúdo verticalmente ao centro
                ) {
                    Column(
                        modifier = Modifier.weight(2f),
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
                            text = formatarDataCriacao(treino.dataDeCriacao),
                            fontSize = 16.sp,
                            color = Color.White
                        )
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

        Card(
            modifier = Modifier
                .height(90.dp)
                .width(180.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp), // Bordas arredondadas para parecer um botão
            colors = CardDefaults.cardColors(containerColor = Color(0xFF275367)),
            elevation = CardDefaults.elevatedCardElevation(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navCtrlDrawer.navigate(AcademiaRotas.TELA_ADICIONAR_TREINO) {
                            popUpTo(AcademiaRotas.TELA_HOME) { inclusive = true }
                        }
                    },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Adicionar Treino",
                        tint = Color(0xFFf5f5f5),
                        modifier = Modifier
                            .size(50.dp)
                            .scale(1.3f)
                            .padding(6.dp)
                    )
                }
                IconButton(
                    onClick = {
                        navCtrlDrawer.navigate(AcademiaRotas.TELA_LISTAR_TREINO) {
                            popUpTo(AcademiaRotas.TELA_HOME) { inclusive = true }
                        }
                    },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Listar Treino",
                        tint = Color(0xFFf5f5f5),
                        modifier = Modifier
                            .size(50.dp)
                            .scale(1f)
                            .padding(6.dp)
                    )
                }
            }
        }
    }
}
//
//@Composable
//fun WorkoutCard(workoutName: String, date: String, duration: String) {
//
//
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0x63275367)),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(5.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically // Alinha o conteúdo verticalmente ao centro
//        ) {
//            Column(
//                modifier = Modifier.weight(2f),
//                horizontalAlignment = Alignment.Start
//            ) {
//                Text(
//                    text = workoutName,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White,
//                    style = TextStyle(
//                        shadow = Shadow(
//                            color = Color(0x8B000000),
//                            offset = Offset(10f, 4f),
//                            blurRadius = 5f
//                        )
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = date,
//                    fontSize = 16.sp,
//                    color = Color.White
//                )
//                Text(
//                    text = "Duração: $duration",
//                    fontSize = 16.sp,
//                    color = Color.White
//                )
//            }
//
//            // Espaço flexível entre o texto e o ícone
//            Spacer(modifier = Modifier.weight(1f))
//
//            IconButton(
//                onClick = {
////                    navCtrlDrawer.navigate(AcademiaRotas.TELA_TREINO) {
////                        popUpTo(AcademiaRotas.TELA_HOME) { inclusive = true }
////                    }
//                },
//                modifier = Modifier
//                    .size(40.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.ArrowForward,
//                    contentDescription = "Opções",
//                    tint = Color.White,
//                    modifier = Modifier.size(30.dp)
//                )
//            }
//        }
//    }
//}








