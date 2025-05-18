package com.example.josh_s8066611finalassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.josh_s8066611finalassignment.data.model.EntityItem
import com.example.josh_s8066611finalassignment.repository.DashboardRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var dashboardRepository: DashboardRepository
    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        dashboardRepository = mockk()
        viewModel = DashboardViewModel(dashboardRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadDashboard with valid keypass returns list of entities`() = runTest {
        // Arrange
        val keypass = "validKey"
        val mockEntities = listOf(
            EntityItem(
                title = "Test Book",
                author = "Test Author",
                genre = "Fiction",
                publicationYear = 2024,
                description = "Test Description"
            )
        )
        coEvery { dashboardRepository.getDashboard(keypass) } returns Result.success(mockEntities)

        // Act
        viewModel.loadDashboard(keypass)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val result = viewModel.entities.value
        assertTrue(result?.isSuccess == true)
        assertEquals(mockEntities, result?.getOrNull())
    }

    @Test
    fun `loadDashboard with invalid keypass returns failure`() = runTest {
        // Arrange
        val keypass = "invalidKey"
        coEvery { dashboardRepository.getDashboard(keypass) } returns Result.failure(Exception("Invalid keypass"))

        // Act
        viewModel.loadDashboard(keypass)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val result = viewModel.entities.value
        assertTrue(result?.isFailure == true)
        assertEquals("Invalid keypass", result?.exceptionOrNull()?.message)
    }

    @Test
    fun `loadDashboard returns empty list when no entities available`() = runTest {
        // Arrange
        val keypass = "validKey"
        val emptyList = emptyList<EntityItem>()
        coEvery { dashboardRepository.getDashboard(keypass) } returns Result.success(emptyList)

        // Act
        viewModel.loadDashboard(keypass)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val result = viewModel.entities.value
        assertTrue(result?.isSuccess == true)
        assertTrue(result?.getOrNull()?.isEmpty() == true)
    }
} 