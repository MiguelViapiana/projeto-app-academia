package com.example.projeto_app_academia.ui.screen.treino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun AdicionarTreinoScreen(padding : PaddingValues){

   Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(16.dp),
       verticalArrangement = Arrangement.spacedBy(8.dp)

   ) {
       Spacer(modifier = Modifier.height(50.dp))
       Text(" Adicionar exercicio", style = MaterialTheme.typography.titleLarge)

       //Nome
       TextField(
           value = "",
           onValueChange = {},
           label = { Text(text = "Nome do exercício")}
       )
       //Peso
       TextField(
           value = "",
           onValueChange = {},
           label = { Text(text = "Peso")},
           visualTransformation = VisualTransformation.None
       )
       //Repetição
       TextField(
           value = "",
           onValueChange = {},
           label = { Text(text = "Repetição")},
           visualTransformation = VisualTransformation.None
       )

   }
}



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