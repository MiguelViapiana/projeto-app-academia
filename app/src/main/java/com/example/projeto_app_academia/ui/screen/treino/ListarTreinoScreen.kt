package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun ListarTreinoScreen(padding : PaddingValues){

    var treinos = mutableListOf(
        Treino(
            titulo = "Treino A"
        ),
        Treino(
            titulo = "Treino B"
        )
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(treinos) { treino ->
            Row {
                Column {
                    Text(
                        text = treino.titulo,
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp
                    )
                }
            }
        }
    }
}


data class Treino(
    var titulo: String,
    var concluido: Boolean = false,
    var id: Int? = null
)
//@Composable
//fun TelaUmA(padding: PaddingValues) {
//
//    var afazeres = mutableListOf(
//        Afazer(
//            titulo = "Comprar carro",
//            descricao = "Visitar concessionaria",
//            id = 1
//        ),
//        Afazer(
//            titulo = "Lavar roupa",
//            descricao = "Lavar roupa pela manhã",
//            id = 2
//        )
//
//    )
//
//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        items(afazeres) { afazer ->
//            Row {
//                Column {
//                    Text(
//                        text = afazer.titulo,
//                        textAlign = TextAlign.Center,
//                        fontSize = 30.sp
//                    )
//                    Text(
//                        text = afazer.descricao,
//                        fontSize = 20.sp
//                    )
//                }
//
//            }
//
//
//        }
//
////        Text(
////            text = "Tela Um A", Modifier.padding(padding),
////            fontSize = 50.sp
////        )
//    }
//}