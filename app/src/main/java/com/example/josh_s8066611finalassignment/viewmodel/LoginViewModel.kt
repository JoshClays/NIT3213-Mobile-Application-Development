package com.example.josh_s8066611finalassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.josh_s8066611finalassignment.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * LoginViewModel handles the business logic for user authentication.
 * It acts as a bridge between the LoginActivity and LoginRepository.
 * Uses Hilt for dependency injection and Kotlin Coroutines for asynchronous operations.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    // LiveData to observe login results
    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> = _loginResult

    /**
     * Attempts to log in the user with the provided credentials.
     * @param firstName The user's first name
     * @param studentId The user's student ID
     * Launches a coroutine to perform the login operation asynchronously
     * and posts the result to loginResult LiveData
     */
    fun login(firstName: String, studentId: String) {
        viewModelScope.launch {
            val result = repository.login(firstName, studentId)
            _loginResult.postValue(result)
        }
    }
}
