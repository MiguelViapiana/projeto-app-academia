package com.example.projeto_app_academia.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.data.model.Usuario
import com.example.projeto_app_academia.data.repository.usuario.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioViewModel(
    private val repository : UsuarioRepository
) : ViewModel() {

    fun excluir(usuario: Usuario){
        viewModelScope.launch {
            repository.excluirUsuario(usuario)
        }
    }

    suspend fun buscarUsuarioPorId(usuarioId: Int): Usuario {
        return withContext(Dispatchers.IO){
            repository.buscarUsuarioPorId(usuarioId)
        }
    }

    suspend fun buscarUsuarioPorEmailSenha(email: String, senha: String): Usuario{
        return withContext(Dispatchers.IO){
            repository.buscarUsuarioPorEmailSenha(email, senha)
        }
    }

    fun gravar(usuarioSalvar: Usuario){
        viewModelScope.launch {
            repository.gravarUsuario(usuarioSalvar)
        }
    }

    suspend fun realizarLogin(email: String, senha: String): Boolean{
        return withContext(Dispatchers.IO){
            repository.realizarLogin(email, senha)
        }
    }

    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> get() = _usuarios

    init {
        viewModelScope.launch {
            repository.listarUsuarios().collectLatest { listaDeUsuarios ->
                _usuarios.value = listaDeUsuarios
            }
        }
    }
}