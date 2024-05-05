package com.example.io_project.googleauthmodule.repository

import com.example.io_project.Constants.CREATED_AT
import com.example.io_project.Constants.DISPLAY_NAME
import com.example.io_project.Constants.EMAIL
import com.example.io_project.Constants.PHOTO_URL
import com.example.io_project.googleauthmodule.model.Response.Failure
import com.example.io_project.googleauthmodule.model.Response.Success
import com.example.io_project.Constants.SIGN_IN_REQUEST
import com.example.io_project.Constants.SIGN_UP_REQUEST
import com.example.io_project.Constants.USERS
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.Goal
import com.example.io_project.dataclasses.Task
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.tasks.await

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private val db: FirebaseFirestore
) : AuthRepository {
    override val isUserAuthenticatedInFirebase = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Success(signUpResult)
            } catch (e: Exception) {
                Failure(e)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): SignInWithGoogleResponse {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val user = authResult.user
            if (user != null) {
                val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
                if (isNewUser) {
                    addUserToFirestore(user)
                }
                Success(true)
            } else {
                Failure(Exception("User is null"))
            }
        } catch (e: Exception) {
            Failure(e)
        }
    }

    private suspend fun addUserToFirestore(user: FirebaseUser) {
        try {
            val emptyUserData = mapOf(
                "regular" to mapOf<String, List<Event>>(
                    "monday" to listOf<Event>(),
                    "tuesday" to listOf<Event>(),
                    "wednesday" to listOf<Event>(),
                    "thursday" to listOf<Event>(),
                    "friday" to listOf<Event>(),
                    "saturday" to listOf<Event>(),
                    "sunday" to listOf<Event>()
                ),
                "nonregular" to mapOf(
                    "data" to listOf<Event>()
                ),
                "goals" to mapOf(
                    "unfinished" to listOf<Goal>(),
                    "completed" to listOf<Goal>()
                ),
                "tasks" to listOf<Task>(),
                "friends" to listOf<String>(),
                "groups" to listOf<String>()
            )
            db.collection(USERS).document(user.uid).set(emptyUserData).await()
        } catch (e: Exception) {
           Failure(e)
        }
    }
}


fun FirebaseUser.toUser() = mapOf(
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString(),
    CREATED_AT to serverTimestamp()
)