package com.example.projeto_app_academia.ui.screen.util

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projeto_app_academia.AcademiaRotas
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
 fun AcademiaTopBar(drawerState: DrawerState, navCtrlDrawer: NavController) {

    val escopo = rememberCoroutineScope()

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                escopo.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier.size(35.dp)
                )
            }
        },

        title = {
            TextButton(
                onClick = {
                    navCtrlDrawer.navigate(AcademiaRotas.TELA_HOME)
                }
            )
            {
                Text(
                    text = "WorkoutWise",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }


        },
        actions = {
            IconButton(onClick = {
                navCtrlDrawer.navigate(AcademiaRotas.TELA_lOGIN)
            }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Login",
                    tint = Color.White,
                    modifier = Modifier.size(35.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color(0xFF275367)),
        modifier = Modifier.padding()
            .clip(RoundedCornerShape(bottomStart = 18.dp, bottomEnd = 18.dp))
    )
}