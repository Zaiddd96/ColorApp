package com.example.colorapp.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.colorapp.ColorRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class ColorViewModel(private val repository: ColorRepository) : ViewModel() {
    val colors = repository.getAllColors()
    val unsyncedCount = repository.getUnsyncedCount()

    fun addRandomColor() {
        viewModelScope.launch {
            val color = String.format("#%06X", Random.nextInt(0xFFFFFF + 1))
            repository.addColor(color, System.currentTimeMillis())
        }
    }
}

