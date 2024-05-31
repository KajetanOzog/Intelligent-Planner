package com.example.io_project.notifications

import com.example.io_project.dataclasses.Alarm

interface AlarmScheduler {
    fun scheduleDailyDataUpdate()
    fun scheduleSingle(alarm: Alarm)
    fun scheduleWeekly(alarm: Alarm)
    fun scheduleDaily(alarm: Alarm)

    fun cancel(alarm: Alarm)
}