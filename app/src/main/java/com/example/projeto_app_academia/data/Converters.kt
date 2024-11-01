package com.example.projeto_app_academia.data

import androidx.room.TypeConverter
import com.example.projeto_app_academia.data.model.Exercicio
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromExercicioList(value: MutableList<Exercicio>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toExercicioList(value: String): MutableList<Exercicio> {
        val listType = object : TypeToken<MutableList<Exercicio>>() {}.type
        return Gson().fromJson(value, listType)
    }
}