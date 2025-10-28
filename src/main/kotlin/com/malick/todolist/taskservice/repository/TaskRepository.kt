package com.malick.todolist.taskservice.repository

import com.malick.todolist.taskservice.model.Task
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : CrudRepository<Task, Long>