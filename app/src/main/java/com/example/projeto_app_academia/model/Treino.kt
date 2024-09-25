package com.example.projeto_app_academia.model

class Treino {
    var id: Int? = null
    var nome: String
    var exercicios: MutableList<Exercicio> = mutableListOf()

    constructor(nome: String) {
        this.nome = nome
    }
}