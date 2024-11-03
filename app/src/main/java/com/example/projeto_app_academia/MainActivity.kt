package com.example.projeto_app_academia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.projeto_app_academia.data.AcademiaDatabse.Companion.abrirBancoDeDados
import com.example.projeto_app_academia.data.repository.categoria.LocalCategoriaRepository
import com.example.projeto_app_academia.data.repository.exercicio.LocalExcercicioRepository
import com.example.projeto_app_academia.data.repository.treino.LocalTreinoRepository
import com.example.projeto_app_academia.ui.mvvm.CategoriaViewModel
import com.example.projeto_app_academia.ui.mvvm.ExercicioViewModel
import com.example.projeto_app_academia.ui.mvvm.TreinoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = abrirBancoDeDados(this)

        val localTreinoRepository = LocalTreinoRepository(db.TrenioDao())
        val treinoViewModel = TreinoViewModel(localTreinoRepository)

        val localCategoriaRepository = LocalCategoriaRepository(db.CategoriaDao())
        val categoriaViewModel = CategoriaViewModel(localCategoriaRepository)

        val localExcericioRepository = LocalExcercicioRepository(db.ExercicioDao())
        val exercicioViewModel = ExercicioViewModel(localExcericioRepository)


        setContent {
            AcademiaNavigation(treinoViewModel, categoriaViewModel, exercicioViewModel)
        }
    }
}