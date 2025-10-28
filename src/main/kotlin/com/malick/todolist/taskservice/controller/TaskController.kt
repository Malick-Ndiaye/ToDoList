package com.malick.todolist.taskservice.controller

import com.malick.todolist.taskservice.model.Task
import com.malick.todolist.taskservice.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = ["*"])
class TaskController(private val repository: TaskRepository) {

    @GetMapping
    fun getAllTasks(): Iterable<Task> =
        repository.findAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody task: Task): Task =
        repository.save(task)

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody updated: Task): Task? {
        val existing = repository.findById(id).orElse(null) ?: return null
        val newTask = existing.copy(
            title = updated.title,
            description = updated.description,
            completed = updated.completed
        )
        return repository.save(newTask)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable id: Long) =
        repository.deleteById(id)

}