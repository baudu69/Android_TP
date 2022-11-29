package com.carlos.tp2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlos.tp2.data.User
import com.carlos.tp2.database.UserDao
import kotlinx.coroutines.*

class IdentityViewModel(
    private val database: UserDao,
    private val userId: Long = 0L
): ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        initializeUser()
    }

    fun onGender(gender: String) {
        _user.value?.gender = gender
    }

    private fun initializeUser() {
        uiScope.launch {
            _user.value = getUserFromDatabase()
            Log.i("IdentityViewModel", "User initialized! : ${user.value?.firstname}")
        }
    }


    private suspend fun getUserFromDatabase(): User {
        return withContext(Dispatchers.IO) {
            var user = database.get(userId)
            if (user == null) {
                user = User()
                user.id = insert(user)
            }
            user
        }
    }

    private suspend fun insert(user: User): Long {
        var id: Long
        withContext(Dispatchers.IO) {
            id = database.insert(user)
        }
        return id
    }

    private suspend fun update(user: User) {
        withContext(Dispatchers.IO) {
            database.update(user)
        }
    }

    private val _navigateToPersonalDataFragment = MutableLiveData<Long>()
    val navigateToPersonalDataFragment: LiveData<Long>
        get() = _navigateToPersonalDataFragment

    fun doneNavigating() {
        _navigateToPersonalDataFragment.value = null
    }
    fun onValidateIdentity() {
        uiScope.launch {
            val user = user.value ?: return@launch
            if(user.firstname.isNullOrEmpty())
                return@launch
            if(user.lastname.isNullOrEmpty())
                return@launch
            update(user)
            _navigateToPersonalDataFragment.value = user.id
        }
    }

    private val _navigateToOtherActivity = MutableLiveData<User>()
    val navigateToOtherActivity: LiveData<User>
        get() = _navigateToOtherActivity
    fun doneValidateNavigating() {
        _navigateToOtherActivity.value = null
    }
    fun onValidate() {
        uiScope.launch {
            val user = user.value ?: return@launch
            if(user.gender.isNullOrEmpty())
                return@launch
            update(user)
            _navigateToOtherActivity.value = user
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("IdentityViewModel", "IdentityViewModel destroyed!")
        viewModelJob.cancel()
    }
}