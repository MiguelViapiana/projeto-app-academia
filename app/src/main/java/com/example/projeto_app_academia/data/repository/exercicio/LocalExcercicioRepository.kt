package com.example.projeto_app_academia.data.repository.exercicio

import com.example.projeto_app_academia.data.dao.ExercicioDao
import com.example.projeto_app_academia.data.model.Exercicio
import kotlinx.coroutines.flow.Flow

class LocalExcercicioRepository(
    private val dao: ExercicioDao
): ExercicioRepository {
    override fun listarExercicio(): Flow<List<Exercicio>> {
        return dao.listarExercicios()
    }

    override suspend fun buscarExercicioPorId(idx: Int): Exercicio {
        return dao.buscarExercicioPorId(idx)
    }

    override suspend fun gravarExercicio(exercicio: Exercicio) {
        return dao.gravarEditarExercicio(exercicio)
    }

    override suspend fun excluirExercicio(exercicio: Exercicio) {
        return dao.excluirExercicio(exercicio)
    }

    override suspend fun atualizarExercicio(exercicio: Exercicio) {
        return dao.updateExercicio(exercicio)
    }
}