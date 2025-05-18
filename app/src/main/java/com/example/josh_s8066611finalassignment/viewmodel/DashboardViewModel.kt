package com.example.josh_s8066611finalassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.josh_s8066611finalassignment.data.model.EntityItem
import com.example.josh_s8066611finalassignment.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * DashboardViewModel handles the business logic for the dashboard screen.
 * Responsibilities include:
 * - Loading and managing the list of books
 * - Error handling for data operations
 * - Providing LiveData for UI updates
 * Uses Hilt for dependency injection and Coroutines for async operations.
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    // LiveData to observe the list of books and their loading state
    private val _entities = MutableLiveData<Result<List<EntityItem>>>()
    val entities: LiveData<Result<List<EntityItem>>> = _entities

    /**
     * Loads the dashboard data using the provided keypass.
     * @param keypass The authentication token received from login
     * Launches a coroutine to fetch data asynchronously and
     * updates the entities LiveData with the result
     */
    fun loadDashboard(keypass: String) {
        viewModelScope.launch {
            val result = dashboardRepository.getDashboard(keypass)
            _entities.postValue(result)
        }
    }
}
