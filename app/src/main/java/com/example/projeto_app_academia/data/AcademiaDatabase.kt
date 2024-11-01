package com.example.projeto_app_academia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.projeto_app_academia.data.dao.TreinoDao
import com.example.projeto_app_academia.data.model.Treino


@Database(entities = [Treino::class], version = 1)
@TypeConverters(Converters::class)
abstract class AcademiaDatabse : RoomDatabase(){
    abstract fun TrenioDao(): TreinoDao

    companion object{
        fun abrirBancoDeDados(context: Context): AcademiaDatabse{
            return Room.databaseBuilder(
                context.applicationContext,
                AcademiaDatabse::class.java, "workoutWise.db"
            ).build()
        }
    }
}

