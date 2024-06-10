package com.example.io_project

import com.example.io_project.datamanagement.parseJson
import org.junit.Test

class JsonTest
{
    @Test
    fun parseJson_parsesCorrectly()
    {
        val json = "{\"latitude\":52.52,\"longitude\":13.419998,\"generationtime_ms\":0.0789165496826172,\"utc_offset_seconds\":0,\"timezone\":\"GMT\",\"timezone_abbreviation\":\"GMT\",\"elevation\":38,\"current_units\":{\"time\":\"iso8601\",\"interval\":\"seconds\",\"temperature_2m\":\"°C\"},\"current\":{\"time\":\"2024-06-09T22:15\",\"interval\":900,\"temperature_2m\":13.1,\"weather_code\": 0},\"daily_units\":{\"time\":\"iso8601\",\"weather_code\":\"wmo code\",\"temperature_2m_max\":\"°C\"},\"daily\":{\"time\":[\"2024-06-09\",\"2024-06-10\",\"2024-06-11\",\"2024-06-12\",\"2024-06-13\",\"2024-06-14\",\"2024-06-15\"],\"weather_code\": [2, 80, 80, 3, 3, 3, 3],\"temperature_2m_max\": [20.7, 17.6, 17.8, 18.6, 20.7, 23.8, 27.1]}}"
        val expectedTemperature = 13.4
        val expectedCode = 0
        val expectedForecastTemp = arrayOf(20.7, 17.6, 17.8, 18.6, 20.7, 23.8, 27.1)
        val expectedForecastCode = arrayOf(2, 80, 80, 3, 3, 3, 3)

        parseJson(json,
            onSuccess = {temp, wc, arr1, arr2 ->
                assert(expectedTemperature == temp)
                assert(expectedCode == wc)
                assert(expectedForecastTemp.contentEquals(arr1))
                assert(expectedForecastCode.contentEquals(arr2))
            },
            onError = {}
        )
    }
}