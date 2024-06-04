import com.example.io_project.dataclasses.Event
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

//evencik bez id prosze
suspend fun addEventToGroup(groupID: String, event: Event) {
    val firestore = FirebaseFirestore.getInstance()
    event.eventID = UUID.randomUUID().toString()
    val groupsDocumentRef = firestore.collection("metadata").document("groups")
    try {
        groupsDocumentRef.update("$groupID.events", FieldValue.arrayUnion(event)).await()
        println("Event added to group: $groupID")
    } catch (e: Exception) {
        println("Error when adding event to group: ${e.message}")
        e.printStackTrace()
    }
}


