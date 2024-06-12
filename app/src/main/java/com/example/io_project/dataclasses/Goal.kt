package com.example.io_project.dataclasses

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Goal(
    @SerializedName("goal_name") // Custom JSON field name for Goal
    override val name: String = "",
    val deadline: String = "",
    var done: Boolean = false
) : Activity(name) {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}
