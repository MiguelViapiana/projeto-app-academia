package com.example.projeto_app_academia.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Treino(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val nome: String,
    var exercicios: MutableList<Exercicio> = mutableListOf()

    ){
    constructor() : this(null, "")
    }