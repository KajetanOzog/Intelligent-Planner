import com.example.io_project.dataclasses.Event
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


suspend fun addEventToGroup(groupID: String, event: Event) {
    val firestore = FirebaseFirestore.getInstance()
    val groupsDocumentRef = firestore.collection("metadata").document("groups")
    try {
        groupsDocumentRef.update("$groupID.events", FieldValue.arrayUnion(event)).await()
    } catch (e: Exception) {
        println("Błąd podczas dodawania wydarzenia: ${e.message}")
        e.printStackTrace()
    }
}


