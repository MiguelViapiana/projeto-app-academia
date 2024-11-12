package com.example.projeto_app_academia.ui.screen.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object UsuarioSession {
    private val _usuarioId = MutableStateFlow(0)
    val usuarioId: StateFlow<Int> get() = _usuarioId

    fun logar(id: Int) {
        _usuarioId.value = id
    }
}