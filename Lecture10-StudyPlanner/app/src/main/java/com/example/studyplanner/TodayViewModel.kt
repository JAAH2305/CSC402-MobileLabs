package com.example.studyplanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodayViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TaskRepository(
        StudyDatabase.getDatabase(application).studyTaskDao()
    )

    private val mutableUiState = MutableStateFlow(TodayUiState())
    val uiState = mutableUiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allTasks.collect { tasks ->
                mutableUiState.update { it.copy(isLoading = false, tasks = tasks) }
            }
        }
    }

    fun onAddTask(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            repository.add(title.trim(), "Mobile Apps")
            mutableUiState.update { it.copy(message = "Task added") }
        }
    }

    fun onTaskCheckedChange(task: StudyTask, done: Boolean) {
        viewModelScope.launch {
            repository.setDone(task, done)
            if (done) {
                mutableUiState.update { it.copy(message = "Marked as done") }
            }
        }
    }

    fun onMessageShown() {
        mutableUiState.update { it.copy(message = null) }
    }
}
