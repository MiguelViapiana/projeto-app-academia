package com.example.projeto_app_academia.data.model

class Exercicio(
    var id: Int? = null,
    var nome: String,
    var categoriaId: Categoria,
    var series: List<Serie> = mutableListOf()
)