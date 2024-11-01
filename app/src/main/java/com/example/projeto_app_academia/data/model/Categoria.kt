package com.example.projeto_app_academia.data.model

class Categoria(
    var id: Int? = null,
    var nome: String,
    var exercicios: List<Exercicio> = mutableListOf()
)