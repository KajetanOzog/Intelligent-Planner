import android.util.Log
import com.example.io_project.dataclasses.Event
import com.example.io_project.datamanagement.getDayOfWeek
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

// Function to add an event to Firestore
suspend fun addEventToFirestore(userID: String, event: Event, isRegular: Boolean) {
    val firestore = FirebaseFirestore.getInstance()
    // Generate a unique event ID
    event.eventID = UUID.randomUUID().toString()
    val userDocumentRef = firestore.collection("users").document(userID)
    try {
        val formattedDate = getDayOfWeek(event.date)
        if (isRegular) {
            // Adding a regular event
            Log.d("AddEventToFirestore", "Adding regular event for day: $formattedDate")
            userDocumentRef.update("regular.$formattedDate", FieldValue.arrayUnion(event)).await()
        } else {
            // Adding a non-regular event
            Log.d("AddEventToFirestore", "Adding nonregular event")
            userDocumentRef.update("nonregular.data", FieldValue.arrayUnion(event)).await()
        }
        Log.d("AddEventToFirestore", "Event added")
    } catch (e: Exception) {
        Log.d("AddEventToFirestore", "Error when adding event: ${e.message}")
    }
}
