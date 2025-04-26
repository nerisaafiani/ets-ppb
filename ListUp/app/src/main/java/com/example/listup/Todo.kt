package com.example.listup

data class Todo (
    val title: String,
    val deadline: String,
    var isChecked: Boolean = false
)