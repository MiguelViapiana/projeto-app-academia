package com.example.projeto_app_academia.data.repository.categoria

import com.example.projeto_app_academia.data.model.Categoria
import kotlinx.coroutines.flow.Flow

interface CategoriaRepository {

    fun listarCategoria(): Flow<List<Categoria>>

    suspend fun buscarCategoriaPorId(idx: Int): Categoria?

    suspend fun gravarCategoria(categoria: Categoria)

    suspend fun excluirCategoria(categoria: Categoria)
}
