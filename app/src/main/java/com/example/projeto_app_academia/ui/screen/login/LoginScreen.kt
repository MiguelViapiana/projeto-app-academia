package com.example.projeto_app_academia.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projeto_app_academia.AcademiaRotas
import com.example.projeto_app_academia.data.model.Usuario
import com.example.projeto_app_academia.ui.mvvm.UsuarioViewModel
import com.example.projeto_app_academia.ui.screen.util.AcademiaTopBar
import com.example.projeto_app_academia.ui.screen.util.UsuarioSession
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun LoginScreen(
    drawerState: DrawerState,
    navController: NavController,
    usuarioViewModel: UsuarioViewModel
) {
    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navController) },
        content = {padding -> ConteudoPrincipal(padding, navController, usuarioViewModel) }
    )
}

@Composable
private fun ConteudoPrincipal(
    padding : PaddingValues,
    navController: NavController,
    usuarioViewModel: UsuarioViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var login by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Realize o login da sua conta", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    login = usuarioViewModel.realizarLogin(email, senha)
                    if(login){
                        var usu: Usuario = usuarioViewModel.buscarUsuarioPorEmailSenha(email, senha)
                        usu.id?.let { UsuarioSession.logar(it) }
                        navController.navigate(AcademiaRotas.TELA_HOME)
                    }
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF275367) // Azul específico
            )
        ) {
            Text(text = "Logar")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate(AcademiaRotas.TELA_SIGNUP)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF275367) // Azul específico
            )
        ) {
            Text(text = "Cadastrar-se")
        }
    }
}

