package com.example.projeto_app_academia.model

class Categoria(
    var id: Int? = null,
    var nome: String,
    var exercicios: List<Exercicio> = mutableListOf()
)