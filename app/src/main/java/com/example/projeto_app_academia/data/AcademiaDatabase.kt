package com.example.projeto_app_academia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.projeto_app_academia.data.dao.TreinoDao
import com.example.projeto_app_academia.data.model.Treino


@Database(entities = [Treino::class], version = 2)
@TypeConverters(Converters::class)
abstract class AcademiaDatabse : RoomDatabase(){
    abstract fun TrenioDao(): TreinoDao

    companion object{
        fun abrirBancoDeDados(context: Context): AcademiaDatabse{
            return Room.databaseBuilder(
                context.applicationContext,
                AcademiaDatabse::class.java, "workoutWise.db"
            )
                .addMigrations(MIGRATION_1_2)
                .build()
        }
    }
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Adicione a nova coluna com um valor padrão
        db.execSQL("ALTER TABLE Treino ADD COLUMN dataDeCriacao INTEGER NOT NULL DEFAULT 0")
    }
}

