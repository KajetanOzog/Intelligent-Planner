package com.example.io_project.datamanagement

fun getNameFromUserName(
    userName: String
): String {
    val returnName = userName.substringBefore(" ")
    return  if (returnName.length > 13) returnName.take(10) + "..." else returnName
}