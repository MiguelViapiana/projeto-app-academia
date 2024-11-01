package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projeto_app_academia.ui.mvvm.TreinoViewModel
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar
import com.example.projeto_app_academia.ui.screen.util.TreinoBottomBar


@Composable
fun ListarTreinoScreen(
    drawerState: DrawerState,
    navCtrlDrawer: NavHostController,
    viewModel: TreinoViewModel)
{
    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navCtrlDrawer) },
        content = {paddingValues -> ConteudoPrincipalListar(paddingValues, viewModel)},
       // bottomBar = {TreinoBottomBar(navCtrlBottomNav, currentScreen)}
    )
}

@Composable
private fun ConteudoPrincipalListar(padding: PaddingValues, viewModel: TreinoViewModel) {

    var coroutineScope = rememberCoroutineScope()
    val treinos by viewModel.treinos.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(treinos){ treino ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = treino.nome,
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp
                    )
                }
            }

        }
//        for(treino in treinos){
//            Row {
//                Column {
//                    Text(
//                        text = treino.nome
//                        textAlign = TextAlign.Center,
//                        fontSize = 30.sp
//                    )
//                }
//            }
//        }
    }
}

