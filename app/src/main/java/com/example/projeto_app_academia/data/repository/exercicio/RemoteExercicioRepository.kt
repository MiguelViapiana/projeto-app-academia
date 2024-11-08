package com.example.projeto_app_academia.data.repository.exercicio

import com.example.projeto_app_academia.data.model.Exercicio
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RemoteExercicioRepository() : ExercicioRepository {

    val db = FirebaseFirestore.getInstance()
    val exercicioColletion = db.collection("exercicio")

    override fun listarExercicio(): Flow<List<Exercicio>> = callbackFlow{
        val listener = exercicioColletion.addSnapshotListener{
            dados, erros ->
            if(erros != null){
                close(erros)
                return@addSnapshotListener
            }
            if(dados != null){
                val exercicios = dados.documents.mapNotNull { dado ->
                    dado.toObject(Exercicio::class.java)
                }
                trySend(exercicios).isSuccess
            }
        }
        awaitClose{listener}
    }

    private suspend fun getId(): Int{
        val dados = exercicioColletion.get().await()
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }


    override suspend fun buscarExercicioPorId(idx: Int): Exercicio? {
        val dados = exercicioColletion. document(idx.toString()).get().await()
        return dados.toObject(Exercicio::class.java)
    }

    override suspend fun gravarExercicio(exercicio: Exercicio) {
        val document: DocumentReference
        if(exercicio.id == null){
            exercicio.id = getId()
            document = exercicioColletion.document(exercicio.id.toString())
        }else{
            document = exercicioColletion.document(exercicio.id.toString())
        }
        document.set(exercicio).await()
    }

    override suspend fun excluirExercicio(exercicio: Exercicio) {
        exercicioColletion.document(exercicio.id.toString()).delete().await()
    }

    override suspend fun atualizarExercicio(exercicio: Exercicio) {
        val document = exercicioColletion.document(exercicio.id.toString())
        document.set(exercicio).await()
    }
}