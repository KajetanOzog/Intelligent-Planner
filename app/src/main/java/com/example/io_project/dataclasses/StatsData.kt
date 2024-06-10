package com.example.io_project.dataclasses

import androidx.compose.ui.graphics.Color
import com.example.io_project.ui.components.PieChartInput

object StatsData
{
    var tasksCompleted: Int = 0
    var maxStreak: Int = 0
    var tasks: MutableList<Task>? = null
    var allEvents: MutableList<Event>? = null
    var dayEvents: MutableList<Event>? = null
    var weekEvents: MutableList<Event>? = null
    var monthEvents: MutableList<Event>? = null
    var yearEvents: MutableList<Event>? = null

    var dayCat: MutableList<PieChartInput> = mutableListOf(
        PieChartInput(Color(231, 74, 84, 255), 0, "Szkoła"),
        PieChartInput(Color(70, 165, 232, 255), 0, "Praca"),
        PieChartInput(Color(82, 223, 117, 255), 0, "Sport"),
        PieChartInput(Color(249, 161, 53, 255), 0, "Znajomi"),
        PieChartInput(Color(177, 76, 210, 255), 0, "Inne")
    )
    var weekCat: MutableList<PieChartInput> = mutableListOf(
        PieChartInput(Color(231, 74, 84, 255), 0, "Szkoła"),
        PieChartInput(Color(70, 165, 232, 255), 0, "Praca"),
        PieChartInput(Color(82, 223, 117, 255), 0, "Sport"),
        PieChartInput(Color(249, 161, 53, 255), 0, "Znajomi"),
        PieChartInput(Color(177, 76, 210, 255), 0, "Inne")
    )
    var monthCat: MutableList<PieChartInput> = mutableListOf(
        PieChartInput(Color(231, 74, 84, 255), 0, "Szkoła"),
        PieChartInput(Color(70, 165, 232, 255), 0, "Praca"),
        PieChartInput(Color(82, 223, 117, 255), 0, "Sport"),
        PieChartInput(Color(249, 161, 53, 255), 0, "Znajomi"),
        PieChartInput(Color(177, 76, 210, 255), 0, "Inne")
    )
    var yearCat: MutableList<PieChartInput> = mutableListOf(
        PieChartInput(Color(231, 74, 84, 255), 0, "Szkoła"),
        PieChartInput(Color(70, 165, 232, 255), 0, "Praca"),
        PieChartInput(Color(82, 223, 117, 255), 0, "Sport"),
        PieChartInput(Color(249, 161, 53, 255), 0, "Znajomi"),
        PieChartInput(Color(177, 76, 210, 255), 0, "Inne")
    )
}