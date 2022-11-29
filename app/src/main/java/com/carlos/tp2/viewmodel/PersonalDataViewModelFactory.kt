package com.carlos.tp2.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.carlos.tp2.data.User

class PersonalDataViewModelFactory(private val user: User): ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonalDataViewModel::class.java)) {
            return PersonalDataViewModel(user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}