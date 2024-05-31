package com.example.io_project.datamanagement

import android.util.Log
import com.example.io_project.dataclasses.Group
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

//Przekazujcie mi grupe ale bez groupID (mozecie dac na ""), ja je sam ustawie

suspend fun addGroup(group: Group, userID: String) {
    val groupId = UUID.randomUUID().toString()
    group.groupID = groupId

    val firestore = FirebaseFirestore.getInstance()
    val metadataRef = firestore.collection("metadata").document("groups")

    val groupData = mapOf(
        groupId to group
    )
    metadataRef.set(groupData, SetOptions.merge())
    Log.d("AddGroup", "2: users/${userID}/groups")

    // Dodawanie do dokumentu uzytkownika TODO(sprawdz czy sie nie gryzie z addUserToGroup)
    val usersDocumentRef = firestore.collection("users").document(userID)
    usersDocumentRef.update("groups", FieldValue.arrayUnion(groupId))
}