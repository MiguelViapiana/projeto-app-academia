package com.example.projeto_app_academia.ui.screen.signup

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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SignUpScreen(
    drawerState: DrawerState,
    navController: NavController,
    usuarioViewModel: UsuarioViewModel

) {
    Scaffold(
        topBar = { AcademiaTopBar(drawerState, navController) },
        content = { padding -> ConteudoPrincipal(padding, navController, usuarioViewModel) }
    )
}

@Composable
private fun ConteudoPrincipal(
    padding: PaddingValues,
    navController: NavController,
    usuarioViewModel: UsuarioViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Realize o seu cadastro", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome completo") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") }
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
                coroutineScope.launch{
                    var usuarioSalvar = Usuario(
                        id = null,
                        nomeCompleto = nome,
                        email = email,
                        cpf = cpf,
                        senha = senha
                    )
                    usuarioViewModel.gravar(usuarioSalvar)
                    navController.navigate(AcademiaRotas.TELA_lOGIN)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF275367) // Azul específico
            )
        ) {
            Text(text = "Cadastrar-se")
        }
    }
}

