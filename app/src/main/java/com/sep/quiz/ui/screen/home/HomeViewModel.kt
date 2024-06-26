package com.sep.quiz.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sep.quiz.domain.entiry.CategoryEntity
import com.sep.quiz.domain.usecase.FetchCategoriesUseCase
import com.sep.quiz.ui.utils.UiState
import com.sep.quiz.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<UiState<List<CategoryEntity>>>(UiState.Initialize)
    val categories = _categories.asStateFlow()

    init {
        fetchCategories()
    }

    fun fetchCategories() {
        viewModelScope.launch {
            _categories.value = UiState.Loading
            when(val result = fetchCategoriesUseCase.invoke()) {
                is ResultState.Exception -> {
                    _categories.value = UiState.Failed(error = result.error.localizedMessage.orEmpty())
                }
                is ResultState.Failure -> {
                    _categories.value = UiState.Failed(error = result.error)
                }
                is ResultState.Success -> {
                    _categories.value = UiState.Success(data = result.data)
                }
            }
        }
    }

}