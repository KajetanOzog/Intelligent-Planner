package com.example.io_project.dataclasses
import com.google.gson.annotations.SerializedName

open class Activity(
    @SerializedName("activity_name") // Custom JSON field name for Activity
    open val name: String = ""
)

