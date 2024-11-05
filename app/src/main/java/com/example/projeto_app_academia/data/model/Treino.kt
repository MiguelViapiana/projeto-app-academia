package com.example.projeto_app_academia.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity
data class Treino(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val nome: String,
    var exercicioIds: String = "",
    val dataDeCriacao: Long = System.currentTimeMillis()


    ) {
    constructor() : this(null, "")


    fun PegarExercicioIds(): MutableList<Int> {
        return if (exercicioIds.isNotEmpty()) {
            // Converte JSON string de volta para lista de Ints
            Gson().fromJson(exercicioIds, object : TypeToken<MutableList<Int>>() {}.type)
                ?: mutableListOf()
        } else {
            mutableListOf()
        }
    }

    fun setExercicioIds(ids: MutableList<Int>) {
        exercicioIds = Gson().toJson(ids)
    }

    fun converterListaParaString(ids: List<Int>): String {
        return ids.joinToString(separator = ", ")
    }
}