package com.carlos.tp2.viewmodel

import androidx.lifecycle.ViewModelProvider

class IdentityViewModelFactory: ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IdentityViewModel::class.java)) {
            return IdentityViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}