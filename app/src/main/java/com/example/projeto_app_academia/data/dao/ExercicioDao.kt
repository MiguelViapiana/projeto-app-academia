package com.example.projeto_app_academia.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.projeto_app_academia.data.model.Exercicio
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercicioDao {

    //Listar
    @Query("select * from exercicio")
    fun listarExercicios(): Flow<List<Exercicio>>

    //Buscar por id
    @Query("select * from exercicio where id = :idx")
    suspend fun buscarExercicioPorId(idx: Int): Exercicio

    //]gravar Update Insert
    @Upsert
    suspend fun gravarEditarExercicio(exercicio: Exercicio)

    //Deletar
    @Delete
    suspend fun excluirExercicio(exercicio: Exercicio)

    @Query("SELECT * FROM exercicio WHERE id IN (:ids)")
    suspend fun buscarExerciciosPorIds(ids: List<Int>): List<Exercicio>

    @Update
    suspend fun updateExercicio(exercicio: Exercicio)

    @Query("SELECT * FROM exercicio WHERE syncStatus = 0")
    fun getUnsyncedExercicios(): List<Exercicio>
}