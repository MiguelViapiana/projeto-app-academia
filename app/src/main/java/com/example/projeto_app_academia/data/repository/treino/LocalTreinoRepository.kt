package com.example.projeto_app_academia.data.repository.treino

import com.example.projeto_app_academia.data.dao.TreinoDao
import com.example.projeto_app_academia.data.model.Treino
import kotlinx.coroutines.flow.Flow

class LocalTreinoRepository(
    private val dao: TreinoDao
): TreinoRepository {
    override fun listarTreinos(): Flow<List<Treino>> {
        return dao.listarTreinos()
    }

    override suspend fun buscarTreinoPorId(idx: Int): Treino {
        return dao.buscarTreinoPorId(idx)
    }

    override suspend fun gravarTreino(treino: Treino) {
        return dao.gravarTreino(treino)
    }

    override suspend fun excluirTreino(treino: Treino) {
        return dao.excluirTreino(treino)
    }
}