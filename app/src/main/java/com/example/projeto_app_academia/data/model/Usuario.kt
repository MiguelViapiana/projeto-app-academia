package com.example.projeto_app_academia.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario (

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val nomeCompleto: String,
    val email: String,
    val cpf: String,
    val senha: String
){
    constructor() : this(null, "", "", "", "")
}