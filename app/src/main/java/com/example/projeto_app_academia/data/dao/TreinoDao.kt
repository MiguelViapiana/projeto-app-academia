package com.example.projeto_app_academia.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.projeto_app_academia.data.model.Treino
import kotlinx.coroutines.flow.Flow

@Dao
interface TreinoDao {

    //Listar
    @Query("select * from treino")
    fun listarTreinos(): Flow<List<Treino>>

    //Buscar por Id
    @Query("select * from treino where id = :idx")
    suspend fun buscarTreinoPorId(idx: Int): Treino

    //Gravar @Update @Insert
    @Upsert
    suspend fun gravarTreino(treino: Treino)

    //Deletar
    @Delete
    suspend fun excluirTreino(treino: Treino)

}