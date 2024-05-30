package com.example.io_project.dataclasses

import androidx.compose.ui.graphics.Color
import com.example.io_project.ui.components.PieChartInput

object StatsData
{
    var tasksCompleted: Int? = null
    var maxStreak: Int? = null
    var tasks: MutableList<Task>? = null
    var allEvents: MutableList<Event>? = null
    var dayEvents: MutableList<Event>? = null
    var weekEvents: MutableList<Event>? = null
    var monthEvents: MutableList<Event>? = null
    var yearEvents: MutableList<Event>? = null

    private val defaultPieChartInputs = mutableListOf(
        PieChartInput(Color(231, 74, 84, 255), 0, "Szkoła"),
        PieChartInput(Color(70, 165, 232, 255), 0, "Praca"),
        PieChartInput(Color(82, 223, 117, 255), 0, "Sport"),
        PieChartInput(Color(249, 161, 53, 255), 0, "Znajomi"),
        PieChartInput(Color(177, 76, 210, 255), 0, "Inne")
    )
    var dayCat: MutableList<PieChartInput> = defaultPieChartInputs
    var weekCat: MutableList<PieChartInput> = defaultPieChartInputs
    var monthCat: MutableList<PieChartInput> = defaultPieChartInputs
    var yearCat: MutableList<PieChartInput> = defaultPieChartInputs
}