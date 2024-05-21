import com.example.io_project.dataclasses.Event
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

suspend fun addEventToFirestore(userID: String, event: Event, isRegular: Boolean) {
    val firestore = FirebaseFirestore.getInstance()
    println(userID)
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

fun getDayOfWeek(date: String): String {
    val formatter = DateTimeFormatter.ofPattern("EEE, MMM d yyyy", Locale.ENGLISH)
    val localDate = LocalDate.parse(date, formatter)
    return when (localDate.dayOfWeek) {
        DayOfWeek.MONDAY -> "monday"
        DayOfWeek.TUESDAY -> "tuesday"
        DayOfWeek.WEDNESDAY -> "wednesday"
        DayOfWeek.THURSDAY -> "thursday"
        DayOfWeek.FRIDAY -> "friday"
        DayOfWeek.SATURDAY -> "saturday"
        DayOfWeek.SUNDAY -> "sunday"
    }
}
