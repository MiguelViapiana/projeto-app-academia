package com.example.projeto_app_academia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.projeto_app_academia.data.dao.CategoriaDao
import com.example.projeto_app_academia.data.dao.ExercicioDao
import com.example.projeto_app_academia.data.dao.TreinoDao
import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.data.model.Categoria
import com.example.projeto_app_academia.data.model.Exercicio
import java.util.concurrent.Executors
import kotlin.reflect.KParameter

@Database(entities = [Treino::class, Categoria::class, Exercicio::class], version = 4)
@TypeConverters(Converters::class)
abstract class AcademiaDatabse : RoomDatabase(){
    abstract fun TrenioDao(): TreinoDao
    abstract fun CategoriaDao(): CategoriaDao
    abstract fun ExercicioDao(): ExercicioDao

    companion object{
        fun abrirBancoDeDados(context: Context): AcademiaDatabse{
            return Room.databaseBuilder(
                context.applicationContext,
                AcademiaDatabse::class.java, "workoutWise.db"
            )
                //.fallbackToDestructiveMigration()
                .build()
        }
    }
}

