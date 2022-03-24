package com.daria.bak.fruitsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.daria.bak.fruitsapp.fruit.business.FruitViewModel
import com.daria.bak.fruitsapp.fruitList.business.FruitListViewModel
import com.daria.bak.fruitsapp.fruitList.ui.FruitListState
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.lang.Error

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FruitListViewModelTest {

    lateinit var sut: FruitListViewModel
    lateinit var mockFruitListClient: MockFruitListClient

    @Before
    fun setup() {
        mockFruitListClient = MockFruitListClient()
        sut = FruitListViewModel(mockFruitListClient)
    }
    @Test
    fun getFruitList_CallsClient() {
        // When
        sut.getFruitList()

        // Then
        assert(mockFruitListClient.getFruitListCalled)
    }

    @Test
    fun getFruitList_WhenClientReturnsError_ShowErrorState() {
        // Given
        mockFruitListClient.getFruitListReturnValue = Result.failure(Error())

        // When
        sut.getFruitList()

        // Then
        assertEquals(sut.fruitListState.value, FruitListState.Error("Something went wrong. Please try again later."));
    }
}