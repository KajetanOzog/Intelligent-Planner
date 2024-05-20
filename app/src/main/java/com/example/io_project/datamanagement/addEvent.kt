import com.example.io_project.dataclasses.Event
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek
import java.util.Locale

suspend fun addEventToFirestore(userID: String, event: Event, isRegular: Boolean) {
    val firestore = FirebaseFirestore.getInstance()
    val userDocumentRef = firestore.collection("users").document(userID)

    val eventData = hashMapOf<String, Any>(
        "name" to event.name,
        "category" to event.category,
        "color" to event.color,
        "place" to event.place,
        "time" to event.time,
        "endDate" to event.endDate,
        "weekly" to event.weekly,
        "reminder" to event.reminder,
        "alarm" to event.alarm,
        "reminderTime" to event.reminderTime,
        "visible" to event.visible,
        "description" to event.description
    )

    try {
        if (isRegular) {
            val dayOfWeek = getDayOfWeek(event.date)
            val regularEventsRef = userDocumentRef.collection("regular")
            val dayEventsRef = regularEventsRef.document(dayOfWeek)
            dayEventsRef.update("events", FieldValue.arrayUnion(eventData)).await()
        } else {
            val nonRegularEventsRef = userDocumentRef.collection("nonregular")
            val dateEventsRef = nonRegularEventsRef.document(event.date)
            dateEventsRef.update("events", FieldValue.arrayUnion(eventData)).await()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


fun getDayOfWeek(date: String): String {
    val formatter = DateTimeFormatter.ofPattern("EEE, MMM d yyyy", Locale.ENGLISH)
    val localDate = LocalDate.parse(date, formatter)
    val dayOfWeek = localDate.dayOfWeek
    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> "monday"
        DayOfWeek.TUESDAY -> "tuesday"
        DayOfWeek.WEDNESDAY -> "wednesday"
        DayOfWeek.THURSDAY -> "thursday"
        DayOfWeek.FRIDAY -> "friday"
        DayOfWeek.SATURDAY -> "saturday"
        DayOfWeek.SUNDAY -> "sunday"
    }
}