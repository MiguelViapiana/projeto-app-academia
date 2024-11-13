package com.example.projeto_app_academia.data.repository.usuario

import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.data.model.Usuario
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {

    fun listarUsuarios(): Flow<List<Usuario>>

    suspend fun buscarUsuarioPorId(idx: Int): Usuario?

    suspend fun buscarUsuarioPorEmailSenha(email: String, senha: String): Usuario?

    suspend fun gravarUsuario(usuario: Usuario)

    suspend fun excluirUsuario(usuario: Usuario)

    suspend fun realizarLogin(email: String, senha: String): Boolean

}