package com.example.io_project.ui.screens.profilescreen

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.io_project.Constants.TAG
import com.example.io_project.datamanagement.getUserFile
import com.example.io_project.datamanagement.importUserFile
import com.example.io_project.googleauthmodule.model.Response.Loading
import com.example.io_project.googleauthmodule.model.Response.Success
import com.example.io_project.googleauthmodule.repository.ProfileRepository
import com.example.io_project.googleauthmodule.repository.RevokeAccessResponse
import com.example.io_project.googleauthmodule.repository.SignOutResponse
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: ProfileRepository
) : ViewModel() {
    val displayName get() = repo.displayName
    val photoUrl get() = repo.photoUrl

    var signOutResponse by mutableStateOf<SignOutResponse>(Success(false))
        private set
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Success(false))
        private set

    fun signOut() = viewModelScope.launch {
        signOutResponse = Loading
        signOutResponse = repo.signOut()
    }

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Loading
        revokeAccessResponse = repo.revokeAccess()
    }

    fun createDataTextFile(context: Context): Boolean {
        return try {
            Log.d("FILE", context.getExternalFilesDir(null).toString())
            val file = File(context.getExternalFilesDir(null), "savedDatabase.txt")
            viewModelScope.launch {
                Firebase.auth.currentUser?.let {
                    FileOutputStream(file).use { output ->
                        output.write(getUserFile(it.uid)?.toByteArray())
                    }
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun importDataFromTextFile(uri: Uri, context: Context): String {
        try {
            val stringBuilder = StringBuilder()
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    var line: String? = reader.readLine()
                    while (line != null) {
                        stringBuilder.append(line)
                        line = reader.readLine()
                    }
                }
            }
            val database = stringBuilder.toString()
            Log.d("FILE", database)
            viewModelScope.launch {
                Firebase.auth.currentUser?.let {
                    importUserFile(it.uid, database)
                }
            }
            return stringBuilder.toString()
        } catch (e: Exception) {
            Log.e(TAG, "${e.message}")
            e.printStackTrace()
            return ""
        }
    }

}