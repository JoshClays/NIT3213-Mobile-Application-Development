package com.example.josh_s8066611finalassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.josh_s8066611finalassignment.repository.LoginRepository
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
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var loginRepository: LoginRepository
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        loginRepository = mockk()
        viewModel = LoginViewModel(loginRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login with valid credentials returns success`() = runTest {
        // Arrange
        val username = "testUser"
        val password = "testPass"
        coEvery { loginRepository.login(username, password) } returns Result.success("Login successful")

        // Act
        viewModel.login(username, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val result = viewModel.loginResult.value
        assertTrue(result?.isSuccess == true)
        assertEquals("Login successful", result?.getOrNull())
    }

    @Test
    fun `login with invalid credentials returns failure`() = runTest {
        // Arrange
        val username = "wrongUser"
        val password = "wrongPass"
        coEvery { loginRepository.login(username, password) } returns Result.failure(Exception("Invalid credentials"))

        // Act
        viewModel.login(username, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val result = viewModel.loginResult.value
        assertTrue(result?.isFailure == true)
        assertEquals("Invalid credentials", result?.exceptionOrNull()?.message)
    }
} 