package com.example.projeto_app_academia.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Exercicio(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val nome: String,
    val categoriaId: Int,
    val series: Int,
    val repeticoes: Int
){
    constructor() : this(null, "", 0, 0, 0)
}