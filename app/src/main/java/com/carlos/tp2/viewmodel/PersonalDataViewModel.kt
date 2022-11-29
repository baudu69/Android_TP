package com.carlos.tp2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.carlos.tp2.data.User

class PersonalDataViewModel(userParam: User): ViewModel() {
    val user: User = userParam
    init {
        Log.i("PersonalDataViewModel", "PersonalDataViewModel created!")
    }

    fun onGender(gender: String) {
        user.gender = gender
    }
}