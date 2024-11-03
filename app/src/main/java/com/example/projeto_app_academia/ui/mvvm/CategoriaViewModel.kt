package com.example.projeto_app_academia.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto_app_academia.data.model.Categoria
import com.example.projeto_app_academia.data.repository.categoria.CategoriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriaViewModel (
    private val repository: CategoriaRepository
): ViewModel() {

    fun excluir(categoria: Categoria){
        viewModelScope.launch {
            repository.excluirCategoria(categoria)
        }
    }

    suspend fun buscarCategoriaPorId(categoriaId: Int): Categoria {
        return withContext(Dispatchers.IO){
            repository.buscarCategoriaPorId(categoriaId)
        }
    }

    fun gravar(categoriaSalvar: Categoria){
        viewModelScope.launch {
            repository.gravarCategoria(categoriaSalvar)
        }
    }

    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> get() = _categorias

    init {
        viewModelScope.launch {
            repository.listarCategoria().collectLatest { listaDeCategoria ->
                _categorias.value = listaDeCategoria
            }
        }
    }
}
