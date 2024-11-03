package com.example.projeto_app_academia.data.repository.exercicio
import com.example.projeto_app_academia.data.model.Exercicio
import kotlinx.coroutines.flow.Flow

interface ExercicioRepository {

    fun listarExercicio(): Flow<List<Exercicio>>

    suspend fun buscarExercicioPorId(idx: Int): Exercicio

    suspend fun gravarExercicio(exercicio: Exercicio)

    suspend fun excluirExercicio(exercicio: Exercicio)
}