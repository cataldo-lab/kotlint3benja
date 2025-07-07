package com.example.taskapp.utils

import Task
import android.graphics.Paint
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.taskapp.model.Task

@BindingAdapter("android:checked")
fun setChecked(checkBox: CheckBox, isChecked: Boolean) {
    if (checkBox.isChecked != isChecked) {
        checkBox.isChecked = isChecked
    }
}

@BindingAdapter("taskTitle")
fun setTaskTitle(textView: TextView, task: Task?) {
    task?.let {
        textView.text = it.title

        // Agregar efecto de tachado si la tarea está completada
        if (it.isCompleted) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}

@BindingAdapter("taskCompleted")
fun setTaskCompleted(textView: TextView, isCompleted: Boolean) {
    if (isCompleted) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        textView.alpha = 0.6f
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        textView.alpha = 1.0f
    }
}

@BindingAdapter("textColor")
fun setTextColor(textView: TextView, isCompleted: Boolean) {
    val color = if (isCompleted) {
        android.graphics.Color.GRAY
    } else {
        android.graphics.Color.BLACK
    }
    textView.setTextColor(color)
}