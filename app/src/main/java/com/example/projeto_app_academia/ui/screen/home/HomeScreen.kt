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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projeto_app_academia.AcademiaRotas
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen(drawerState: DrawerState, navController: NavController) {
    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navController) },
        content = {padding -> ConteudoPrincipal(padding, navController) }
    )
}

@Composable
private fun ConteudoPrincipal(padding: PaddingValues, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf5f5f5))
            .padding(16.dp),
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

        Spacer(modifier = Modifier.height(16.dp))

        WorkoutCard(
            workoutName = "Leg Day",
            date = "Sep 21, 2024",
            duration = "45 minutes"
        )
        WorkoutCard(
            workoutName = "Upper Body",
            date = "Sep 18, 2024",
            duration = "50 minutes"
        )
        WorkoutCard(
            workoutName = "Cardio",
            date = "Sep 17, 2024",
            duration = "30 minutes"
        )
        Row (
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {
                    navController.navigate(AcademiaRotas.TELA_TREINO) {
                        popUpTo(AcademiaRotas.TELA_HOME) { inclusive = true }
                    }},
                modifier = Modifier.size(60.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Adicionar Treino",
                    tint = Color(0xFF275367),
                    modifier = Modifier
                        .size(60.dp)
                        .scale(1.3f)
                )
            }
        }
    }
}

@Composable
fun WorkoutCard(workoutName: String, date: String, duration: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x5E858585)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = workoutName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF275367)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = date,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = "Duração: $duration",
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}






