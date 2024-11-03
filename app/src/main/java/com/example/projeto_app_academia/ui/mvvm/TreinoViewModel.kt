package com.example.projeto_app_academia.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto_app_academia.data.repository.treino.TreinoRepository
import com.example.projeto_app_academia.data.model.Treino
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TreinoViewModel(
    private val repository: TreinoRepository
): ViewModel() {

    fun excluir(treino: Treino){
        viewModelScope.launch {
            repository.excluirTreino(treino)
        }
    }

    suspend fun buscarTreinoPorId(treinoId: Int): Treino {
        return withContext(Dispatchers.IO){
            repository.buscarTreinoPorId(treinoId)
        }
    }

    fun gravar(treinoSalvar: Treino){
        viewModelScope.launch {
            repository.gravarTreino(treinoSalvar)
        }
    }

    private val _treinos = MutableStateFlow<List<Treino>>(emptyList())
    val treinos: StateFlow<List<Treino>> get() = _treinos

    init {
        viewModelScope.launch {
            repository.listarTreinos().collectLatest { listaDeTreino ->
                _treinos.value = listaDeTreino
            }
        }
    }
}