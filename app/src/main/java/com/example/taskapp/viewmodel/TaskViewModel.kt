package com.example.taskapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taskapp.database.TaskDatabase
import com.example.taskapp.model.Task
import com.example.taskapp.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.allTasks
        Log.d("TaskViewModel", "ViewModel initialized")
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        Log.d("TaskViewModel", "Inserting task: ${task.title}")
        repository.insertTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        Log.d("TaskViewModel", "Updating task: ${task.title}, completed: ${task.isCompleted}")
        repository.updateTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        Log.d("TaskViewModel", "Deleting task: ${task.title}")
        repository.deleteTask(task)
    }

    fun deleteTaskById(taskId: Int) = viewModelScope.launch {
        Log.d("TaskViewModel", "Deleting task by ID: $taskId")
        repository.deleteTaskById(taskId)
    }
}