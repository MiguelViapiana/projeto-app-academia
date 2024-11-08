package com.example.projeto_app_academia.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Categoria (

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val nome: String
){
    constructor(): this(null, "")
}