package com.example.io_project.dataclasses

import android.net.Uri
import com.google.gson.Gson

data class Goal(
    val name: String = "",
    val deadline: String = "",
    val done: Boolean = false
) {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}
