package com.example.io_project.googleauthmodule.core

import android.util.Log
import com.example.io_project.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())
    }
}