package com.itsyasirali.groceriesapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.itsyasirali.groceriesapp.domain.model.User
import kotlinx.coroutines.tasks.await

class FbRepo {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun registerUser(
        name: String,
        email: String,
        phone: String,
        address: String,
        password: String
    ): Result<Unit> {
        return try {
            val user = User(
                name = name,
                email = email,
                phone = phone,
                address = address,
                password = password
            )

            val docRef = firestore.collection("users").add(user).await()

            user.uid = docRef.id
            firestore.collection("users").document(docRef.id).set(user).await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginUser(email: String, password: String): Result<User> {
        return try {
            val snapshot = firestore.collection("users")
                .whereEqualTo("email", email)
                .whereEqualTo("password", password)
                .get()
                .await()

            if (snapshot.documents.isNotEmpty()) {
                val document = snapshot.documents.first()
                val user = document.toObject(User::class.java)?.copy(uid = document.id)
                if (user != null) {
                    Result.success(user)
                } else {
                    Result.failure(Exception("User data is malformed"))
                }
            } else {
                Result.failure(Exception("Invalid email or password"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
