package com.example.projeto_app_academia.data.repository.categoria

import com.example.projeto_app_academia.data.dao.CategoriaDao
import com.example.projeto_app_academia.data.model.Categoria
import kotlinx.coroutines.flow.Flow

class LocalCategoriaRepository(
    private val dao: CategoriaDao
): CategoriaRepository {
    override fun listarCategoria(): Flow<List<Categoria>> {
        return dao.listarCategorias()
    }

    override suspend fun buscarCategoriaPorId(idx: Int): Categoria {
        return dao.buscarCategoriaPorId(idx)
    }

    override suspend fun gravarCategoria(categoria: Categoria) {
       return dao.gravarEditarCategoria(categoria)
    }

    override suspend fun excluirCategoria(categoria: Categoria) {
        return dao.excluirCategoria(categoria)
    }

}