package com.example.projeto_app_academia.data.repository.usuario

import com.example.projeto_app_academia.data.dao.UsuarioDao
import com.example.projeto_app_academia.data.model.Usuario
import kotlinx.coroutines.flow.Flow

class LocalUsuarioRepository(
    private val dao: UsuarioDao
) : UsuarioRepository {
    override fun listarUsuarios(): Flow<List<Usuario>> {
        return dao.listarUsuarios()
    }

    override suspend fun buscarUsuarioPorId(idx: Int): Usuario {
        return dao.buscarUsuarioPorId(idx)
    }

    override suspend fun buscarUsuarioPorEmailSenha(email: String, senha: String): Usuario {
        return dao.buscarUsuarioPorEmailSenha(email, senha)
    }

    override suspend fun gravarUsuario(usuario: Usuario) {
        return dao.gravarUsuario(usuario)
    }

    override suspend fun excluirUsuario(usuario: Usuario) {
        return dao.excluirUsuario(usuario)
    }

    override suspend fun realizarLogin(email: String, senha: String): Boolean {
        return dao.realizarLogin(email, senha)
    }
}