package com.todo2

import com.todo2.models.Task
import com.todo2.service.TaskService
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/tasks") {
            call.respond(TaskService.getAllTasks())
        }
        post("/tasks/add"){
            val task = call.receive<Task>()
            call.respond(TaskService.createTask(task))
        }
    }
}
