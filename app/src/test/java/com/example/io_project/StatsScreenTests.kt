package com.example.io_project

import co.yml.charts.common.extensions.isNotNull
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.EventPriority
import com.example.io_project.dataclasses.StatsData
import com.example.io_project.dataclasses.Task
import com.example.io_project.ui.screens.statsscreen.BarChartInput
import com.example.io_project.ui.screens.statsscreen.countCategories
import com.example.io_project.ui.screens.statsscreen.countCompleted
import com.example.io_project.ui.screens.statsscreen.filterEvents
import com.example.io_project.ui.screens.statsscreen.getBarChartData
import com.example.io_project.ui.screens.statsscreen.getMaxStreak
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.random.Random

class StatsScreenTests
{
    @Test
    fun getBarChartData_returnsCorrectList()
    {
        StatsData.tasks = mutableListOf(
            Task(name = "1", doneCount = 3),
            Task(name = "2", doneCount = 8),
            Task(name = "3", doneCount = 6)
            )
        val expectedData = listOf(
            BarChartInput(3, "1"),
            BarChartInput(8, "2"),
            BarChartInput(6, "3")
        )
        val barChartData = getBarChartData()
        Assert.assertEquals(expectedData, barChartData)
    }

    @Test
    fun getBarChartData_nullListShouldReturnEmpty()
    {
        StatsData.tasks = null
        val expectedData: List<BarChartInput> = listOf()
        val barChartData = getBarChartData()
        Assert.assertEquals(expectedData, barChartData)
    }

    @Test
    fun countCategories_countsCorrectly()
    {
        val categories = arrayOf("Sport", "Szkoła", "Inne")
        StatsData.dayEvents = mutableListOf()
        StatsData.weekEvents = mutableListOf()
        StatsData.monthEvents = mutableListOf()
        StatsData.yearEvents = mutableListOf()

        val expectedDayCat = mutableMapOf("Sport" to 0, "Szkoła" to 0, "Inne" to 0)
        val expectedWeekCat = mutableMapOf("Sport" to 0, "Szkoła" to 0, "Inne" to 0)
        val expectedMonthCat = mutableMapOf("Sport" to 0, "Szkoła" to 0, "Inne" to 0)
        val expectedYearCat = mutableMapOf("Sport" to 0, "Szkoła" to 0, "Inne" to 0)

        fun increment(map: MutableMap<String, Int>, key: String)
        {
            val count = map[key]
            if (count != null) {
                map[key] = count + 1
            }
        }

        for(i in 0..20)
        {
            val cat = categories[Random.nextInt(categories.size)]
            StatsData.dayEvents!!.add(Event(category = cat, priority = EventPriority.LOW))
            increment(expectedDayCat, cat)
        }

        for(i in 0..30)
        {
            val cat = categories[Random.nextInt(categories.size)]
            StatsData.weekEvents!!.add(Event(category = cat, priority = EventPriority.LOW))
            increment(expectedWeekCat, cat)
        }

        for(i in 0..40)
        {
            val cat = categories[Random.nextInt(categories.size)]
            StatsData.monthEvents!!.add(Event(category = cat, priority = EventPriority.LOW))
            increment(expectedMonthCat, cat)
        }

        for(i in 0..50)
        {
            val cat = categories[Random.nextInt(categories.size)]
            StatsData.yearEvents!!.add(Event(category = cat, priority = EventPriority.LOW))
            increment(expectedYearCat, cat)
        }
        println(StatsData.dayCat[2].value)

        countCategories()

        println(StatsData.dayCat[2].value)

        for(i in 0..2)
        {
            var item = StatsData.dayCat.find { it.description == categories[i] }
            Assert.assertTrue(item?.value == expectedDayCat[categories[i]])

            item = StatsData.weekCat.find { it.description == categories[i] }
            Assert.assertTrue(item?.value == expectedWeekCat[categories[i]])

            item = StatsData.monthCat.find { it.description == categories[i] }
            Assert.assertTrue(item?.value == expectedMonthCat[categories[i]])

            item = StatsData.yearCat.find { it.description == categories[i] }
            Assert.assertTrue(item?.value == expectedYearCat[categories[i]])
        }
    }

    @Test
    fun filterEvents_filtersCorrectly()
    {
        StatsData.dayEvents = mutableListOf()
        StatsData.weekEvents = mutableListOf()
        StatsData.monthEvents = mutableListOf()
        StatsData.yearEvents = mutableListOf()
        val formatter = DateTimeFormatter.ofPattern("EEE, MMM d yyyy", Locale.ENGLISH)
        val currentDate = LocalDate.now()

        val eventList: MutableList<Event> = mutableListOf()
        eventList.add(Event(name = "1", date = currentDate.format(formatter), priority = EventPriority.LOW))
        eventList.add(Event(name = "2", date = currentDate.format(formatter), priority = EventPriority.LOW))
        eventList.add(Event(name = "3", date = currentDate.minusDays(3).format(formatter), priority = EventPriority.LOW))
        eventList.add(Event(name = "4", date = currentDate.minusDays(4).format(formatter), priority = EventPriority.LOW))
        eventList.add(Event(name = "5", date = currentDate.minusDays(6).format(formatter), priority = EventPriority.LOW))
        eventList.add(Event(name = "6", date = currentDate.minusDays(12).format(formatter), priority = EventPriority.LOW))
        eventList.add(Event(name = "7", date = currentDate.minusDays(23).format(formatter), priority = EventPriority.LOW))
        eventList.add(Event(name = "8", date = currentDate.minusDays(31).format(formatter), priority = EventPriority.LOW))
        eventList.add(Event(name = "9", date = currentDate.minusDays(123).format(formatter), priority = EventPriority.LOW))
        eventList.add(Event(name = "10", date = currentDate.minusDays(1242).format(formatter), priority = EventPriority.LOW))

        filterEvents(eventList)

        val expectedDayList = eventList.subList(0, 2)
        val expectedWeekList = eventList.subList(0, 5)
        val expectedMonthList = eventList.subList(0, 7)
        val expectedYearList = eventList.subList(0, 9)

        for(i in expectedDayList.indices)
        {
            val item = StatsData.dayEvents!!.find { it.name == expectedDayList[i].name }
            assert(item.isNotNull())
        }
        for(i in expectedWeekList.indices)
        {
            val item = StatsData.weekEvents!!.find { it.name == expectedWeekList[i].name }
            assert(item.isNotNull())
        }
        for(i in expectedMonthList.indices)
        {
            val item = StatsData.monthEvents!!.find { it.name == expectedMonthList[i].name }
            assert(item.isNotNull())
        }
        for(i in expectedYearList.indices)
        {
            val item = StatsData.yearEvents!!.find { it.name == expectedYearList[i].name }
            assert(item.isNotNull())
        }
    }

    @Test
    fun countCompleted_countsCorrectly()
    {
        val taskList = listOf(
            Task(),
            Task(completed = true),
            Task(),
            Task(completed = true),
            Task(completed = true),
            Task(completed = true),
            Task(),
        )

        assert(countCompleted(taskList.toMutableList()) == 4)
    }

    @Test
    fun getMaxStreak_countsCorrectly()
    {
        val taskList = listOf(
            Task(maxStreak = 1),
            Task(maxStreak = 7),
            Task(maxStreak = 3),
            Task(maxStreak = 2),
            Task(maxStreak = 99),
            Task(maxStreak = 0)
        )

        assert(getMaxStreak(taskList.toMutableList()) == 99)
    }
}