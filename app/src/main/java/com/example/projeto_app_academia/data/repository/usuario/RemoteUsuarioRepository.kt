package com.example.projeto_app_academia.data.repository.usuario
import com.example.projeto_app_academia.data.model.Treino
import com.example.projeto_app_academia.data.model.Usuario
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RemoteUsuarioRepository(): UsuarioRepository {

    val db = FirebaseFirestore.getInstance()
    val usuarioColletion = db.collection("usuario")

    override fun listarUsuarios(): Flow<List<Usuario>> = callbackFlow{
        val listener = usuarioColletion.addSnapshotListener{
                dados, erros ->
            if(erros != null){
                close(erros)
                return@addSnapshotListener
            }
            if(dados != null){
                val usuarios = dados.documents.mapNotNull { dado ->
                    dado.toObject(Usuario::class.java)
                }
                trySend(usuarios).isSuccess
            }
        }
        awaitClose{listener}
    }

    override suspend fun buscarUsuarioPorId(idx: Int): Usuario? {
        val dados = usuarioColletion.document(idx.toString()).get().await()
        return dados.toObject(Usuario::class.java)
    }

    override suspend fun buscarUsuarioPorEmailSenha(email: String, senha: String): Usuario? {
        val querySnapshot = usuarioColletion
            .whereEqualTo("email", email)
            .whereEqualTo("senha", senha)
            .get()
            .await()
        return querySnapshot.documents.firstOrNull()?.toObject(Usuario::class.java)
    }

    suspend fun getId(): Int{
        val dados = usuarioColletion.get().await()
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun gravarUsuario(usuario: Usuario) {
        val document: DocumentReference
        if(usuario.id == null){
            usuario.id = getId()
            document = usuarioColletion.document(usuario.id.toString())
        }else{
            document = usuarioColletion.document(usuario.id.toString())
        }
        document.set(usuario).await()
    }

    override suspend fun excluirUsuario(usuario: Usuario) {
        usuarioColletion.document(usuario.id.toString()).delete().await()
    }

    override suspend fun realizarLogin(email: String, senha: String): Boolean {
        val usuario = buscarUsuarioPorEmailSenha(email, senha)
        return usuario != null
    }
}