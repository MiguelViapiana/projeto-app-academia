package com.example.projeto_app_academia.data.repository.categoria

import com.example.projeto_app_academia.data.model.Categoria
import com.example.projeto_app_academia.data.model.Exercicio
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RemoteCategoriaRepository() : CategoriaRepository {

    val db = FirebaseFirestore.getInstance()
    val categoriaColletion = db.collection("categoria")

    override fun listarCategoria(): Flow<List<Categoria>> = callbackFlow {
        val listener = categoriaColletion.addSnapshotListener{
            dados , erros ->
            if(erros != null){
                close(erros)
                return@addSnapshotListener
            }
            if(dados != null){
                val categorias = dados.documents.mapNotNull { dado ->
                    dado.toObject(Categoria::class.java)
                }
                trySend(categorias).isSuccess
            }
        }
        awaitClose{listener}
    }

    suspend fun getId(): Int{
        val dados = categoriaColletion.get().await()
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun buscarCategoriaPorId(idx: Int): Categoria? {
        val dados = categoriaColletion. document(idx.toString()).get().await()
        return dados.toObject(Categoria::class.java)
    }

    override suspend fun gravarCategoria(categoria: Categoria) {
        val document: DocumentReference
        if(categoria.id == null){
            categoria.id = getId()
            document = categoriaColletion.document(categoria.id.toString())
        }else{
            document = categoriaColletion.document(categoria.id.toString())
        }
        document.set(categoria).await()
    }

    override suspend fun excluirCategoria(categoria: Categoria) {
        categoriaColletion.document(categoria.id.toString()).delete().await()
    }

}