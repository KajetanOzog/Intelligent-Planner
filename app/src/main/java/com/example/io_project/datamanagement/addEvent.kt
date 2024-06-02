import com.example.io_project.dataclasses.Event
import com.example.io_project.datamanagement.getDayOfWeek
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


suspend fun addEventToFirestore(userID: String, event: Event, isRegular: Boolean) {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)
    try {
        val formattedDate = getDayOfWeek(event.date)
        if (isRegular) {
            println("Dodawanie wydarzenia regularnego na dzień $formattedDate")
            userDocumentRef.update("regular.$formattedDate", FieldValue.arrayUnion(event)).await()
        } else {
            println("Dodawanie wydarzenia nieregularnego")
            userDocumentRef.update("nonregular.data", FieldValue.arrayUnion(event)).await()
        }
        println("Wydarzenie dodane pomyślnie")
    } catch (e: Exception) {
        println("Błąd podczas dodawania wydarzenia: ${e.message}")
        e.printStackTrace()
    }
}


