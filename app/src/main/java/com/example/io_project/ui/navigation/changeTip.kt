package com.example.io_project.ui.navigation

fun changeTip(forward: Boolean, currentTipNumber: Int): Int {
    if (currentTipNumber > 5 || currentTipNumber < 1)
        return 1
    return if (forward) {
        if (currentTipNumber == 5) 1
        else currentTipNumber + 1
    }
    else {
        if (currentTipNumber == 1) 5
        else currentTipNumber - 1
    }
}