package com.example.projeto_app_academia.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercicio(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val nome: String,
    val categoriaId: Int,
    var treinoId: Int?,
    val series: Int,
    val repeticoes: Int
){
    constructor() : this(null, "", 0, 0, 0, 0)
}