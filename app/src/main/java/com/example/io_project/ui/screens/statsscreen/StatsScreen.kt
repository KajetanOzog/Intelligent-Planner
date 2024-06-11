package com.example.io_project.ui.screens.statsscreen

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import com.example.io_project.ui.theme.IO_ProjectTheme
import com.example.io_project.R
import com.example.io_project.dataclasses.Event
import com.example.io_project.dataclasses.StatsData
import com.example.io_project.dataclasses.Task
import com.example.io_project.datamanagement.fetchAllEvents
import com.example.io_project.datamanagement.getTasks
import com.example.io_project.ui.components.BottomBar
import com.example.io_project.ui.components.PieChart
import com.example.io_project.ui.components.TopBar
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

@Composable
fun StatsScreen(
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var period by remember { mutableIntStateOf(1) }
    updateData()

    Scaffold(
        topBar = {
            TopBar(
                text = "Statystyki",
                navigateBack = navigateBack,
                canNavigateBack = true
            )
        },
        bottomBar = {
            BottomBar(
                navigateTo = navigateTo,
                currentScreenName = "long_term_screen"
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(ScrollState(0))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = modifier
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            )
            {
                for (i in 1..4) {
                    ElevatedButton(
                        onClick = { period = i },
                        modifier = modifier
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (period == i) MaterialTheme.colorScheme.secondary
                            else MaterialTheme.colorScheme.inverseOnSurface,
                            contentColor = if (period == i) MaterialTheme.colorScheme.onSecondary
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(2.dp)
                    )
                    {
                        Text(
                            text = when (i) {
                                1 -> "Dzień"
                                2 -> "Tydzień"
                                3 -> "Miesiąc"
                                else -> "Rok"
                            },
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }

            Row(
                modifier = modifier
                    .padding(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Column(modifier = modifier.weight(1f)) {
                    Completed(tasks = StatsData.tasks)
                }
                Column(modifier = modifier.weight(1f)) {
                    Streak(tasks = StatsData.tasks)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(dimensionResource(id = R.dimen.padding_small))
            ) {
                when(period)
                {
                    1 -> PieChart(
                        input = StatsData.dayCat,
                        radius = 500f,
                        innerRadius = 300f,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                    2 -> PieChart(
                        input = StatsData.weekCat,
                        radius = 500f,
                        innerRadius = 300f,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                    3 -> PieChart(
                        input = StatsData.monthCat,
                        radius = 500f,
                        innerRadius = 300f,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                    else -> PieChart(
                        input = StatsData.yearCat,
                        radius = 500f,
                        innerRadius = 300f,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }
            }

            BarChartInit()
        }
    }
}

fun updateData()
{
    runBlocking {
        val userID = Firebase.auth.currentUser?.uid.toString()
        StatsData.allEvents = fetchAllEvents(userID)?.toMutableList()
        StatsData.tasks = getTasks(userID)?.toMutableList()
        StatsData.allEvents?.let {
            filterEvents(it)
            countCategories()
        }
    }
}

@Composable
fun BarChartInit() {

    val barData = getBarChartData()
    val chartData: MutableList<BarData> = mutableListOf()
    chartData.add(BarData(Point(0f, 0f)))
    var maxRange = 10
    for (i in barData.indices) {
        if (barData[i].value > maxRange) maxRange = barData[i].value
        chartData.add(
            BarData(
                Point(chartData.size.toFloat(), barData[i].value.toFloat()),
                label = barData[i].label,
                color = Color(74, 5, 250)
            )
        )
    }

    val xAxisData = AxisData.Builder()
        .axisStepSize(60.dp)
        .steps(chartData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .labelData { index -> chartData[index].label }
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(6)
        .labelAndAxisLinePadding(20.dp)
        .labelData { index -> (index * (maxRange / 6)).toString() }
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val barChartData = BarChartData(
        chartData = chartData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        horizontalExtraSpace = 20.dp,
        paddingEnd = 20.dp,
        backgroundColor = MaterialTheme.colorScheme.surface
    )

    Column(
        modifier = Modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(dimensionResource(id = R.dimen.padding_small))
            .fillMaxWidth()
    ) {
        BarChart(
            modifier = Modifier
                .height(340.dp)
                .background(MaterialTheme.colorScheme.inverseOnSurface),
            barChartData = barChartData
        )
    }
}

fun getBarChartData(): List<BarChartInput> {
    val res: MutableList<BarChartInput> = mutableListOf()

    StatsData.tasks?.forEach { task ->
        res.add(BarChartInput(task.doneCount, task.name))
    }

    return res
}

@Composable
fun Completed(tasks: MutableList<Task>?, modifier: Modifier = Modifier) {
    var completed by remember { mutableIntStateOf(StatsData.tasksCompleted) }

    LaunchedEffect(Unit)
    {
        tasks?.let {
            StatsData.tasksCompleted = countCompleted(tasks)
            completed = StatsData.tasksCompleted
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(dimensionResource(id = R.dimen.padding_small))
            .fillMaxWidth()
    )
    {
        Text(
            text = "Wykonane zadania dzisiaj:",
            textAlign = TextAlign.Center,
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$completed",
            modifier = modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 28.sp
        )
    }
}

@Composable
fun Streak(tasks: MutableList<Task>?, modifier: Modifier = Modifier) {
    var streak by remember { mutableIntStateOf(StatsData.maxStreak) }

    LaunchedEffect(Unit)
    {
        tasks?.let {
            StatsData.maxStreak = getMaxStreak(tasks)
            streak = StatsData.maxStreak
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(dimensionResource(id = R.dimen.padding_small))
            .fillMaxWidth()
    )
    {
        Text(
            text = "Najdłuższa bieżąca seria:",
            textAlign = TextAlign.Center,
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$streak",
            modifier = modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 28.sp
        )
    }
}

fun countCategories() {
    categoriesToZero()

    StatsData.dayEvents?.let { events ->
        for (i in events.indices) {
            val item = StatsData.dayCat.find { it.description == events[i].category }
            item?.let { it.value++ }
        }
    }

    StatsData.weekEvents?.let { events ->
        for (i in events.indices) {
            val item = StatsData.weekCat.find { it.description == events[i].category }
            item?.let { it.value++ }
        }
    }

    StatsData.monthEvents?.let { events ->
        for (i in events.indices) {
            val item = StatsData.monthCat.find { it.description == events[i].category }
            item?.let { it.value++ }
        }
    }


    StatsData.yearEvents?.let { events ->
        for (i in events.indices) {
            val item = StatsData.yearCat.find { it.description == events[i].category }
            item?.let { it.value++ }
        }
    }

    for (i in StatsData.yearCat.indices)
    {
        Log.d(StatsData.yearCat[i].description, StatsData.yearCat[i].value.toString())
    }
}

fun categoriesToZero()
{
    for(i in 0..4)
    {
        StatsData.dayCat[i].value = 0
        StatsData.weekCat[i].value = 0
        StatsData.monthCat[i].value = 0
        StatsData.yearCat[i].value = 0
    }
}

fun filterEvents(events: List<Event>) {
    val formatter = DateTimeFormatter.ofPattern("EEE, MMM d yyyy", Locale.ENGLISH)
    val now = LocalDate.now()
    val nowFormatted = now.format(formatter)

    val dailyEvents = events.filter { it.date == nowFormatted || it.weekly }.toMutableList()
    StatsData.dayEvents =
        dailyEvents.filter { it.date.substring(0, 3) == nowFormatted.substring(0, 3) }
            .toMutableList()

    val oneWeekAgo = now.minusWeeks(1)
    StatsData.weekEvents = events.filter {
        // Log.d("Stats screen", it.date)
        val date = LocalDate.parse(it.date, formatter)
        !date.isAfter(now) && date.isAfter(oneWeekAgo) || it.weekly
    }.toMutableList()

    val oneMonthAgo = now.minusMonths(1)
    StatsData.monthEvents = events.filter {
        val date = LocalDate.parse(it.date, formatter)
        !date.isAfter(now) && date.isAfter(oneMonthAgo) || it.weekly
    }.toMutableList()

    val oneYearAgo = now.minusYears(1)
    StatsData.yearEvents = events.filter {
        val date = LocalDate.parse(it.date, formatter)
        !date.isAfter(now) && date.isAfter(oneYearAgo) || it.weekly
    }.toMutableList()
}

fun countCompleted(tasks: MutableList<Task>): Int {
    var res = 0
    for (i in tasks.indices) {
        if (tasks[i].completed) res++
    }
    return res
}

fun getMaxStreak(tasks: MutableList<Task>): Int {
    var res = 0
    for (i in tasks.indices) {
        if (tasks[i].maxStreak > res) res = tasks[i].maxStreak
    }
    return res
}

data class BarChartInput(
    var value: Int,
    val label: String
)

@Preview(showBackground = true)
@Composable
fun StatsScreenPreview() {
    IO_ProjectTheme {
        StatsScreen(navigateTo = {}, navigateBack = {})
    }
}