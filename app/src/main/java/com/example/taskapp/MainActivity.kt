package com.example.taskapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.taskapp.databinding.ActivityMainBinding
import com.example.taskapp.model.Task
import com.example.taskapp.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Usa Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this  // LifecycleOwner

        // Observa las tareas y actualiza el RecyclerView (puedes adaptar tu adapter)
        viewModel.allTasks.observe(this, Observer { tasks ->
            // Actualiza el adapter aquí
        })

        // Ejemplo de agregar tarea desde botón (puedes mejorar esto según tu adapter)
        binding.addButton.setOnClickListener {
            val title = binding.editTask.text.toString()
            if (title.isNotBlank()) {
                val task = Task(title = title)
                viewModel.insertTask(task)
                binding.editTask.text?.clear()
            }
        }
    }
}
