package com.example.io_project.dataclasses

import android.content.Context
import com.example.io_project.notifications.AlarmScheduler
import com.example.io_project.notifications.AlarmSchedulerImpl
import java.time.LocalDateTime

data class Alarm (
    val message: String,
    val time: LocalDateTime,
    val sound: Boolean
)
