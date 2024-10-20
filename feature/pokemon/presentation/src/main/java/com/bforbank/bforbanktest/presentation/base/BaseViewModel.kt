package com.bforbank.bforbanktest.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Base ViewModel class.
 * It requires an [Action] and a [State] type.
 */
abstract class BaseViewModel<Action, State>(initialState: State) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()
    private val stateMutex = Mutex()

    protected suspend fun updateState(reducer: State.() -> State) =
        stateMutex.withLock {
            _uiState.value = reducer(_uiState.value)
        }

    protected fun launch(
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        block: suspend CoroutineScope.() -> Unit
    ): Job =
        viewModelScope.launch(
            context = dispatcher,
            block = block
        )


    abstract fun handle(action: Action)

     fun destroy() {
        super.onCleared()
    }
}