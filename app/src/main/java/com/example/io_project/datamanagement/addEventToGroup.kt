import android.util.Log
import com.example.io_project.dataclasses.Event
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

// Function to add an event to a group in Firestore without specifying ID
suspend fun addEventToGroup(groupID: String, event: Event) {
    val firestore = FirebaseFirestore.getInstance()
    // Generate a unique event ID
    event.eventID = UUID.randomUUID().toString()
    val groupsDocumentRef = firestore.collection("metadata").document("groups")
    try {
        // Update the group document with the new event
        groupsDocumentRef.update("$groupID.events", FieldValue.arrayUnion(event)).await()
        Log.d("AddEventToGroup", "Event added to group: $groupID")
    } catch (e: Exception) {
        Log.d("AddEventToGroup", "Error when adding event to group: ${e.message}")
    }
}
