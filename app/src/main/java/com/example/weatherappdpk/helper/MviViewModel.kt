package com.example.weatherappdpk.helper

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.SharedFlow

interface MviResult
interface MviAction
interface MviIntent
interface ViewState
interface ViewEffect


abstract class MviActionProcessor<A : MviAction, R : MviResult> {
    abstract suspend fun process(actions: ReceiveChannel<A>): ReceiveChannel<R>
}

interface MviViewModel<I : MviIntent, S : ViewState, E : ViewEffect, A : MviAction, R : MviResult> {
    val intent: SendChannel<I>
    val state: S
    val result: SharedFlow<R>
    fun processIntents(): SendChannel<I>
    suspend fun processAction(actions: ReceiveChannel<A>)
    fun states(): ReceiveChannel<S>
    fun effects(): ReceiveChannel<E>
}

sealed class UiState<T> {
    class Nothing<T> : UiState<T>()

    data class Success<T>(val data: T) : UiState<T>()

    data class Error<T>(val showInToast: Boolean, val errorMessage: String, val errorCode: Int) :
        UiState<T>()

    data class Loading<T>(val loading: Boolean) : UiState<T>()
}