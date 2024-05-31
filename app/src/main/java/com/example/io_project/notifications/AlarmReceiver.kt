package com.example.io_project.notifications

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.io_project.R
import com.example.io_project.dataclasses.Task
import com.example.io_project.datamanagement.getUserDocument
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.model.Document

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        val message = intent.getStringExtra("EXTRA_MESSAGE") ?: return
        val sound = intent.getStringExtra("EXTRA_SOUND").toBoolean() ?: return

        if (message == "daily_database_update") {
            updateDatabase(context, intent)
        } else {
            displayNotification(context, intent, message, sound)
        }

    }

    private fun displayNotification(context: Context?, intent: Intent, message: String, addSound: Boolean) {
        val channelId = "event_notifications"
        val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        Log.d("AlarmReceiver", "Message: $message")
        context?.let { ctx ->
            val notificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val builder = NotificationCompat.Builder(ctx, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(message)
                .setContentText("Przypomnienie o wydarzeniu")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            if (addSound) {
                builder.setSound(alarmSound)
            }
            Log.d("AlarmReceiver", "$notificationManager, $builder")
            notificationManager.notify(1, builder.build())
        }
    }

    private fun updateDatabase(context: Context?, intent: Intent) {
        Firebase.auth.currentUser?.let {
            val firestore = FirebaseFirestore.getInstance()
            val tasksCollection =
                firestore.collection("users/${it.uid}/tasks").get() as List<QueryDocumentSnapshot>
            for (task in tasksCollection) {
                if (task.data["done"] == true) {
                    task.reference.update("daysCounter", FieldValue.increment(1))
                    task.reference.update("done", false)
                }
            }
        }
    }
}