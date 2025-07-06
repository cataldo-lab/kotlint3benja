package com.example.taskapp.model

data class Task(
    val id: Int,
    val title: String,
    var isCompleted: Boolean = false
)
