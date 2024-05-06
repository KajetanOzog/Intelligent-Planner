import com.example.io_project.dataclasses.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore


fun addEventToFirestore(userId: String, event: Event, eventType: String, dayOfWeek: String? = null) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    if (currentUser != null && currentUser.uid == userId) {
        val db = FirebaseFirestore.getInstance()
        val eventsCollection = db.collection("users").document(userId)

        if (eventType == "regular" && dayOfWeek != null) {
            val dayEventsDoc = eventsCollection.collection("regular").document(dayOfWeek)

            dayEventsDoc.update("events", FieldValue.arrayUnion(event))
                .addOnSuccessListener {
                    println("Regular event added successfully")
                }
                .addOnFailureListener { e ->
                    println("Error adding regular event: $e")
                }
        } else if (eventType == "nonregular") {
            eventsCollection.collection("nonregular").document("data")
                .update(event.name, FieldValue.arrayUnion(event))
                .addOnSuccessListener {
                    println("Non-regular event added successfully")
                }
                .addOnFailureListener { e ->
                    println("Error adding non-regular event: $e")
                }
        } else {
            println("Invalid event type or day of week")
        }
    } else {
        println("Wrong user ID")
    }
}
