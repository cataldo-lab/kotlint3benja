package com.example.taskapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.adapter.TaskAdapter
import com.example.taskapp.databinding.ActivityMainBinding
import com.example.taskapp.model.Task
import com.example.taskapp.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Configura RecyclerView
        setupRecyclerView()

        // Observa las tareas y actualiza el RecyclerView
        viewModel.allTasks.observe(this, Observer { tasks ->
            taskAdapter.submitList(tasks)
        })

        // Configurar el botón para agregar tareas
        binding.addButton.setOnClickListener {
            val title = binding.editTask.text.toString().trim()
            if (title.isNotBlank()) {
                val task = Task(title = title)
                viewModel.insertTask(task)
                binding.editTask.text?.clear()
            }
        }
    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(
            onTaskClick = { task ->
                // Simply toggle the completion state
                val updatedTask = task.copy(isCompleted = !task.isCompleted)
                viewModel.updateTask(updatedTask)
            },
            onDeleteClick = { task ->
                viewModel.deleteTask(task)
            }
        )

        binding.recyclerView.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}