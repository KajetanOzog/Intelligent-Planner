package com.example.io_project.datamanagement

import com.example.io_project.dataclasses.Group
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import java.util.UUID

//Przekazujcie mi grupe ale bez groupID (mozecie dac na ""), ja je sam ustawie

suspend fun addGroup(group: Group) {
    val groupId = UUID.randomUUID().toString()
    group.groupID = groupId

    val firestore = FirebaseFirestore.getInstance()
    val metadataRef = firestore.collection("metadata").document("groups")

    val groupData = mapOf(
        groupId to group
    )
    metadataRef.set(groupData, SetOptions.merge()).await()

}