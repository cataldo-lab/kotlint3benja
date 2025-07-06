package com.example.taskapp.utils

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
    }
}