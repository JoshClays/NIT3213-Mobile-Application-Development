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

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _entities = MutableLiveData<Result<List<EntityItem>>>()
    val entities: LiveData<Result<List<EntityItem>>> = _entities

    fun loadDashboard(keypass: String) {
        viewModelScope.launch {
            val result = dashboardRepository.getDashboard(keypass)
            _entities.postValue(result)
        }
    }
}
