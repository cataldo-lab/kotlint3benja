package com.example.taskapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.adapter.TaskAdapter
import com.example.taskapp.model.Task
import com.example.taskapp.ui.theme.TaskAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskInput: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskInput = findViewById(R.id.taskInput)
        addButton = findViewById(R.id.addButton)
        recyclerView = findViewById(R.id.recyclerView)

        val tasks = mutableListOf<Task>()
        taskAdapter = TaskAdapter(tasks)
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener{
            val taskTitle = taskInput.text.toString()
            if(taskTitle.isNotEmpty()){
                val newTask = Task(id = tasks.size + 1, title = taskTitle)
                taskAdapter.addTask(newTask)
                taskInput.text.clear()
                taskInput.text.clear()
            }
        }

    }
}
