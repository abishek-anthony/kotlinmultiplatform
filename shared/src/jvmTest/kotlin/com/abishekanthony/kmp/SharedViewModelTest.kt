package com.abishekanthony.kmp

import com.abishekanthony.kmp.api.Api
import com.abishekanthony.kmp.viewModel.navigation.NavigationViewModel
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SharedViewModelTest {

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()
    private lateinit var mockedApiClient: Api
    private lateinit var navigationViewModel: NavigationViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockedApiClient = mock<Api>()
        navigationViewModel = NavigationViewModel(mockedApiClient)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // Testing results of Couroutines Scope
    // This test will run the coroutine in the test dispatcher
    // and will not block the main thread.
    // It will also allow us to control the execution of the coroutine
    // and test the result of the coroutine.
    @Test
    fun test_buttonClick() = runTest {
        everySuspend { mockedApiClient.fetchHello() } returns "Test"

        val resultDeferred = CompletableDeferred<String>()
        navigationViewModel.onButtonClick { result ->
            resultDeferred.complete(result)
        }

        assertEquals(expected = "Test", actual = resultDeferred.await())
        assertNotEquals(illegal = "no", actual = resultDeferred.await())
    }
}
