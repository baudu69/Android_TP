package com.carlos.tp2.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carlos.tp2.database.UserDao

class IdentityViewModelFactory (
    private val dataSource: UserDao,
    private val userId: Long = 0L
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IdentityViewModel::class.java)) {
            return IdentityViewModel(dataSource, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
