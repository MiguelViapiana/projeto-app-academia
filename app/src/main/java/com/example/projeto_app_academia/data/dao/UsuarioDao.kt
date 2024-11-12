package com.example.projeto_app_academia.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.data.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuario")
    fun listarUsuarios(): Flow<List<Usuario>>

    @Query("SELECT * FROM usuario WHERE id = :idx")
    suspend fun buscarUsuarioPorId(idx: Int): Usuario

    @Query("SELECT * FROM usuario WHERE email = :email AND senha = :senha")
    suspend fun buscarUsuarioPorEmailSenha(email: String, senha: String): Usuario

    @Upsert
    suspend fun gravarUsuario(usuario: Usuario)

    //Deletar
    @Delete
    suspend fun excluirUsuario(usuario: Usuario)

    @Query("SELECT COUNT(*) > 0 FROM usuario WHERE email = :email AND senha = :senha")
    suspend fun realizarLogin(email: String, senha: String): Boolean
}