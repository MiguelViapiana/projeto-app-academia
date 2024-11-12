package com.example.projeto_app_academia.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity
data class Treino(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val nome: String,
    val usuarioId: Int?,
    val dataDeCriacao: Long = System.currentTimeMillis()


    ) {
    constructor() : this(null, "", null)

    fun converterListaParaString(ids: List<Int>): String {
        return ids.joinToString(separator = ", ")
    }
}