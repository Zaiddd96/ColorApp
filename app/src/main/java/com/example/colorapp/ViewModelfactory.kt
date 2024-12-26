package com.example.colorapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.colorapp.ui.theme.ColorViewModel

class ColorViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ColorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ColorViewModel(ColorRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}