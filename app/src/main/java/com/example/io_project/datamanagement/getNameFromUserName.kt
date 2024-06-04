package com.example.io_project.datamanagement

fun getNameFromUserName(
    userName: String
): String {
    return userName.substringBefore(" ")
}