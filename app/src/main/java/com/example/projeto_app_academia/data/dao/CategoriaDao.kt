package com.example.projeto_app_academia.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.projeto_app_academia.data.model.Categoria
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriaDao {

    //Listar
    @Query("select * from categoria")
    fun listarCategorias(): Flow<List<Categoria>>

    //Buscar por id
    @Query("select * from categoria where id = :idx")
    suspend fun buscarCategoriaPorId(idx: Int): Categoria

    //]gravar Update Insert
    @Upsert
    suspend fun gravarEditarCategoria(categoria: Categoria)

    //Deletar
    @Delete
    suspend fun excluirCategoria(categoria: Categoria)

}