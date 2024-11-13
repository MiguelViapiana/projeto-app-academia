package com.migas.workoutwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.projeto_app_academia.AcademiaNavigation
import com.example.projeto_app_academia.data.AcademiaDatabse.Companion.abrirBancoDeDados
import com.example.projeto_app_academia.data.repository.categoria.LocalCategoriaRepository
import com.example.projeto_app_academia.data.repository.categoria.RemoteCategoriaRepository
import com.example.projeto_app_academia.data.repository.exercicio.LocalExcercicioRepository
import com.example.projeto_app_academia.data.repository.exercicio.RemoteExercicioRepository
import com.example.projeto_app_academia.data.repository.treino.LocalTreinoRepository
import com.example.projeto_app_academia.data.repository.treino.RemoteTreinoRepository
import com.example.projeto_app_academia.data.repository.usuario.LocalUsuarioRepository
import com.example.projeto_app_academia.data.repository.usuario.RemoteUsuarioRepository
import com.example.projeto_app_academia.ui.mvvm.CategoriaViewModel
import com.example.projeto_app_academia.ui.mvvm.ExercicioViewModel
import com.example.projeto_app_academia.ui.mvvm.TreinoViewModel
import com.example.projeto_app_academia.ui.mvvm.UsuarioViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val isLocal = false

        val db = abrirBancoDeDados(this)

        val localTreinoRepository = LocalTreinoRepository(db.TrenioDao())
        val remoteTreinoRepository = RemoteTreinoRepository()

        val localCategoriaRepository = LocalCategoriaRepository(db.CategoriaDao())
        val remoteCategoriaRepository = RemoteCategoriaRepository()

        val localExcericioRepository = LocalExcercicioRepository(db.ExercicioDao())
        val remoteExercioRepository = RemoteExercicioRepository()

        val localUsuarioRepository = LocalUsuarioRepository(db.UsuarioDao())
        val remoteUsuarioRepository = RemoteUsuarioRepository()

        val exercicioViewModel: ExercicioViewModel
        val treinoViewModel: TreinoViewModel
        val categoriaViewModel: CategoriaViewModel
        val usuarioViewModel : UsuarioViewModel

        if(isLocal){
            treinoViewModel = TreinoViewModel(localTreinoRepository)
            exercicioViewModel = ExercicioViewModel(localExcericioRepository)
            categoriaViewModel = CategoriaViewModel(localCategoriaRepository)
            usuarioViewModel = UsuarioViewModel(localUsuarioRepository)
        }else{
            treinoViewModel = TreinoViewModel(remoteTreinoRepository)
            exercicioViewModel = ExercicioViewModel(remoteExercioRepository)
            categoriaViewModel = CategoriaViewModel(remoteCategoriaRepository)
            usuarioViewModel = UsuarioViewModel(remoteUsuarioRepository)
        }

        setContent {
            AcademiaNavigation(treinoViewModel, categoriaViewModel, exercicioViewModel, usuarioViewModel, 0)
        }
    }
}