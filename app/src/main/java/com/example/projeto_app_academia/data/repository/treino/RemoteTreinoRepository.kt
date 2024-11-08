package com.example.projeto_app_academia.data.repository.treino

import com.example.projeto_app_academia.data.model.Treino
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlin.math.max

class RemoteTreinoRepository(): TreinoRepository {

    val db = FirebaseFirestore.getInstance()
    val treinoColletion = db.collection("treino")

    override fun listarTreinos(): Flow<List<Treino>> = callbackFlow {
        val listener = treinoColletion.addSnapshotListener{
            dados, erros ->
            if(erros != null){
                close(erros)
                return@addSnapshotListener
            }
            if(dados != null){
                val treinos = dados.documents.mapNotNull { dado ->
                    dado.toObject(Treino::class.java)
                }
                trySend(treinos).isSuccess
            }
        }
       awaitClose{listener}
    }

    suspend fun getId(): Int{
        val dados = treinoColletion.get().await()
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun buscarTreinoPorId(idx: Int): Treino? {
        val dados = treinoColletion.document(idx.toString()).get().await()
        return dados.toObject(Treino::class.java)
    }

    override suspend fun gravarTreino(treino: Treino) {
        val document: DocumentReference
        if(treino.id == null){
            treino.id = getId()
            document = treinoColletion.document(treino.id.toString())
        }else{
            document = treinoColletion.document(treino.id.toString())
        }
        document.set(treino).await()
    }

    override suspend fun excluirTreino(treino: Treino) {
        treinoColletion.document(treino.id.toString()).delete().await()
    }
}