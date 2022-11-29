package com.carlos.tp2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlos.tp2.data.User

class IdentityViewModel: ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        Log.i("IdentityViewModel", "IdentityViewModel created!")
        _user.value = User("Doe", "John")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("IdentityViewModel", "IdentityViewModel destroyed!")
    }
}