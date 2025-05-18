package com.example.josh_s8066611finalassignment.repository

import android.util.Log
import com.example.josh_s8066611finalassignment.data.api.ApiService
import com.example.josh_s8066611finalassignment.data.model.DashboardResponse
import com.example.josh_s8066611finalassignment.data.model.EntityItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Response

class DashboardRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var repository: DashboardRepository

    @Before
    fun setup() {
        // Mock Android Log
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
        
        apiService = mockk()
        repository = DashboardRepository(apiService)
    }

    @Test
    fun `getDashboard with valid keypass returns list of entities`() = runTest {
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
        val mockResponse = DashboardResponse(entities = mockEntities, entityTotal = 1)
        
        coEvery { 
            apiService.getDashboard(keypass) 
        } returns Response.success(mockResponse)

        // Act
        val result = repository.getDashboard(keypass)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(mockEntities, result.getOrNull())
    }

    @Test
    fun `getDashboard with invalid keypass returns failure`() = runTest {
        // Arrange
        val keypass = "invalidKey"
        coEvery { 
            apiService.getDashboard(keypass) 
        } returns Response.error(401, mockk(relaxed = true))

        // Act
        val result = repository.getDashboard(keypass)

        // Assert
        assertTrue(result.isFailure)
        assertNotNull(result.exceptionOrNull())
    }

    @Test
    fun `getDashboard with network error returns failure`() = runTest {
        // Arrange
        val keypass = "validKey"
        coEvery { 
            apiService.getDashboard(keypass) 
        } throws Exception("Network error")

        // Act
        val result = repository.getDashboard(keypass)

        // Assert
        assertTrue(result.isFailure)
        assertEquals("Network error", result.exceptionOrNull()?.message)
    }

    @Test
    fun `getDashboard returns empty list when no entities available`() = runTest {
        // Arrange
        val keypass = "validKey"
        val emptyResponse = DashboardResponse(entities = emptyList(), entityTotal = 0)
        coEvery { 
            apiService.getDashboard(keypass) 
        } returns Response.success(emptyResponse)

        // Act
        val result = repository.getDashboard(keypass)

        // Assert
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()?.isEmpty() == true)
    }
} 