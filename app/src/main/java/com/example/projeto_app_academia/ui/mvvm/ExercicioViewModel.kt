package com.example.projeto_app_academia.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto_app_academia.data.model.Exercicio
import com.example.projeto_app_academia.data.repository.exercicio.ExercicioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExercicioViewModel(
    private val repository: ExercicioRepository
): ViewModel() {

    fun excluir(exercicio: Exercicio){
        viewModelScope.launch {
            repository.excluirExercicio(exercicio)
        }
    }

    private suspend fun buscarExercicioPorId(exercicoiId: Int): Exercicio? {
        return withContext(Dispatchers.IO){
            repository.buscarExercicioPorId(exercicoiId)
        }
    }

    fun gravar(exercicioSalvar: Exercicio){
        viewModelScope.launch {
            repository.gravarExercicio(exercicioSalvar)
        }
    }

    private val _exercicios = MutableStateFlow<List<Exercicio>>(emptyList())
    val exercicios: StateFlow<List<Exercicio>> get() = _exercicios

    init {
        viewModelScope.launch {
            repository.listarExercicio().collectLatest { listaDeExercicio ->
                _exercicios.value = listaDeExercicio
            }
        }
    }

    fun removerExercicioDoTreino(exercicioId: Int) {
        viewModelScope.launch {
            val exercicio = buscarExercicioPorId(exercicioId)
            if (exercicio != null) {
                val exercicioAtualizado = exercicio.copy(treinoId = null)
                repository.atualizarExercicio(exercicioAtualizado)
            }
        }
    }

}