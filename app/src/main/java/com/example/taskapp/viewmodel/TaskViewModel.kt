package com.example.taskapp.viewmodel

import Task
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taskapp.database.TaskDatabase
import com.example.taskapp.model.Task
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val db = TaskDatabase.getDatabase(application)
    private val dao = db.taskDao()

    val tasks: LiveData<List<Task>> = dao.getAllTasks()

    fun agregarTarea(task: Task) = viewModelScope.launch {
        dao.insertTask(task)
    }

    fun eliminarTarea(task: Task) = viewModelScope.launch {
        dao.deleteTask(task)
    }
}
