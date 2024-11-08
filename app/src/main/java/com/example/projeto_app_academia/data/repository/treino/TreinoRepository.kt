package com.example.projeto_app_academia.data.repository.treino

import com.example.projeto_app_academia.data.model.Treino
import kotlinx.coroutines.flow.Flow

interface TreinoRepository {

    fun listarTreinos(): Flow<List<Treino>>

    suspend fun buscarTreinoPorId(idx: Int): Treino?

    suspend fun gravarTreino(treino: Treino)

    suspend fun excluirTreino(treino: Treino)

}