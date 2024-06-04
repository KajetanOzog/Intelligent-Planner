import com.example.io_project.dataclasses.Event
import com.example.io_project.datamanagement.getDayOfWeek
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

// prosze evencik bez id sam je ustawie
suspend fun addEventToFirestore(userID: String, event: Event, isRegular: Boolean) {
    val firestore = FirebaseFirestore.getInstance()
    event.eventID = UUID.randomUUID().toString()
    val userDocumentRef = firestore.collection("users").document(userID)
    try {
        val formattedDate = getDayOfWeek(event.date)
        if (isRegular)
        {
            println("Adding regular event for day: $formattedDate")
            userDocumentRef.update("regular.$formattedDate", FieldValue.arrayUnion(event)).await()
        }
        else
        {
            println("Adding nonregular event")
            userDocumentRef.update("nonregular.data", FieldValue.arrayUnion(event)).await()
        }
        println("Event added")
    } catch (e: Exception) {
        println("Error when adding event: ${e.message}")
        e.printStackTrace()
    }
}


