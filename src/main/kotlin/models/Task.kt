package com.todo2.models

import kotlinx.serialization.Serializable

@Serializable
data class Task (
    val id:String,
    val title:String,
    val description:String? = null,
    val completed : Boolean = false
    )