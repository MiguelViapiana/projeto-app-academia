package com.example.projeto_app_academia.data

import androidx.room.TypeConverter
import com.example.projeto_app_academia.data.model.Exercicio
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

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

    @TypeConverter
    fun fromListToString(list: List<Int>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromStringToList(value: String): List<Int> {
        return if (value.isEmpty()) {
            emptyList()
        } else {
            value.split(",").map { it.toInt() }
        }
    }

    @TypeConverter
    fun fromMutableListInt(value: MutableList<Int>): String {
        return value.joinToString(separator = ",")
    }

    @TypeConverter
    fun toMutableListInt(value: String): MutableList<Int> {
        return if (value.isEmpty()) mutableListOf() else value.split(",").map { it.toInt() }.toMutableList()
    }
}