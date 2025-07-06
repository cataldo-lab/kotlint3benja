package com.example.taskapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.adapter.TaskAdapter
import com.example.taskapp.databinding.ActivityMainBinding
import com.example.taskapp.model.Task
import com.example.taskapp.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = taskViewModel
        binding.lifecycleOwner = this // Para observar LiveData desde el layout

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(
            onTaskCheckedChange = { task ->
                taskViewModel.updateTask(task)
            },
            onTaskLongClick = { task ->
                showDeleteConfirmationDialog(task)
            }
        )

        binding.recyclerView.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupObservers() {
        // Observar cambios en la lista de tareas
        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let {
                taskAdapter.submitList(it)
            }
        })
    }

    private fun setupClickListeners() {
        binding.addButton.setOnClickListener {
            val taskTitle = binding.taskInput.text.toString().trim()
            if (taskTitle.isNotEmpty()) {
                val newTask = Task(title = taskTitle)
                taskViewModel.insertTask(newTask)
                binding.taskInput.text.clear()
                Toast.makeText(this, "Tarea agregada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor ingresa una tarea", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDeleteConfirmationDialog(task: Task) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar tarea")
            .setMessage("¿Estás seguro de que quieres eliminar esta tarea?")
            .setPositiveButton("Eliminar") { _, _ ->
                taskViewModel.deleteTask(task)
                Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}