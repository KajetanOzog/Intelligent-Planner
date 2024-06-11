package com.example.io_project.dataclasses
import android.net.Uri
import com.google.gson.Gson

open class Activity(
    open val name: String = ""
) {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}
