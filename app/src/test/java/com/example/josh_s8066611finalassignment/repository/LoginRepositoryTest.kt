package com.example.josh_s8066611finalassignment.repository

import android.util.Log
import com.example.josh_s8066611finalassignment.data.api.ApiService
import com.example.josh_s8066611finalassignment.data.model.LoginRequest
import com.example.josh_s8066611finalassignment.data.model.LoginResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Response

class LoginRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var repository: LoginRepository

    @Before
    fun setup() {
        // Mock Android Log
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0

        apiService = mockk()
        repository = LoginRepository(apiService)
    }

    @Test
    fun `login with valid credentials returns success`() = runTest {
        // Arrange
        val firstName = "Josh"
        val studentId = "s8066611"
        val expectedRequest = LoginRequest(firstName = firstName, studentId = studentId)
        val mockResponse = LoginResponse("test-keypass")
        
        coEvery { 
            apiService.login(expectedRequest)
        } returns Response.success(mockResponse)

        // Act
        val result = repository.login(firstName, studentId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals("test-keypass", result.getOrNull())
    }

    @Test
    fun `login with invalid credentials returns failure`() = runTest {
        // Arrange
        val firstName = "Wrong"
        val studentId = "wrong123"
        val expectedRequest = LoginRequest(firstName = firstName, studentId = studentId)
        
        coEvery { 
            apiService.login(expectedRequest)
        } returns Response.error(401, mockk(relaxed = true))

        // Act
        val result = repository.login(firstName, studentId)

        // Assert
        assertTrue(result.isFailure)
        assertNotNull(result.exceptionOrNull())
    }

    @Test
    fun `login with network error returns failure`() = runTest {
        // Arrange
        val firstName = "Josh"
        val studentId = "s8066611"
        val expectedRequest = LoginRequest(firstName = firstName, studentId = studentId)
        
        coEvery { 
            apiService.login(expectedRequest)
        } throws Exception("Network error")

        // Act
        val result = repository.login(firstName, studentId)

        // Assert
        assertTrue(result.isFailure)
        assertEquals("Network error", result.exceptionOrNull()?.message)
    }
} 