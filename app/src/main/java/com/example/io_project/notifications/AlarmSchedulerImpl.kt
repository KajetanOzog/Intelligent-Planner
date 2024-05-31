package com.example.io_project.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.io_project.dataclasses.Alarm
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Calendar

class AlarmSchedulerImpl(
    private val context: Context
) : AlarmScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun scheduleDailyDataUpdate() {
        Log.d("ScheduleDailyDataUpdate", "Scheduling daily updates...")
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", "daily_data_update")
        }
        val midnight: LocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT)
        val alarmTime = midnight.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000L
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            AlarmManager.INTERVAL_DAY,
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
    override fun scheduleDaily(alarm: Alarm) {
        TODO("Not yet implemented")
    }

    override fun scheduleSingle(alarm: Alarm) {
        Log.d("ScheduleSingle", "Scheduling ${alarm}...")
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", alarm.message)
            putExtra("EXTRA_SOUND", alarm.sound.toString())
        }
        val alarmTime = alarm.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000L
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            PendingIntent.getBroadcast(
                context,
                alarm.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )

        Log.e("Alarm", "Alarm set at $alarmTime")
    }

    override fun scheduleWeekly(alarm: Alarm) {
        Log.d("ScheduleWeekly", "Scheduling ${alarm}...")
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", alarm.message)
        }
        val alarmTime = alarm.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000L
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            AlarmManager.INTERVAL_DAY * 7,
            PendingIntent.getBroadcast(
                context,
                alarm.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }


    override fun cancel(alarmItem: Alarm) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarmItem.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}