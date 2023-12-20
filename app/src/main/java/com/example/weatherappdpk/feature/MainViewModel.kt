package com.example.weatherappdpk.feature

import androidx.lifecycle.ViewModel
import com.example.weatherappdpk.helper.MviActionProcessor
import com.example.weatherappdpk.helper.MviViewModel
import com.example.weatherappdpk.presentation.HomeAction
import com.example.weatherappdpk.presentation.HomeIntent
import com.example.weatherappdpk.presentation.HomeResult
import com.example.weatherappdpk.presentation.HomeViewEffect
import com.example.weatherappdpk.presentation.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val actionProcessor: MviActionProcessor<HomeAction, HomeResult>,
) : ViewModel(), MviViewModel<
        HomeIntent,
        HomeViewState,
        HomeViewEffect,
        HomeAction,
        HomeResult> {

    init {
        states()
    }

    override val intent: SendChannel<HomeIntent> by lazy { processIntents() }
    override val state: HomeViewState = HomeViewState.default()
    override val result: MutableSharedFlow<HomeResult> = MutableSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class, ObsoleteCoroutinesApi::class)
    override fun processIntents(): SendChannel<HomeIntent> {
        return CoroutineScope(Dispatchers.IO).actor {
            consumeEach { homeIntent ->
                val actionProducer = produce {
                    when (homeIntent) {
                        is HomeIntent.GetWeatherDetailsIntent ->
                            send(
                                HomeAction.GetWeatherDetailsAction(
                                    homeIntent.type,
                                    homeIntent.location
                                )
                            )
                    }
                }
                processAction(actionProducer)
            }
        }
    }

    override suspend fun processAction(actions: ReceiveChannel<HomeAction>) {
        actionProcessor.process(actions).consumeEach {
            result.emit(it)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun states(): ReceiveChannel<HomeViewState> {
        return CoroutineScope(Dispatchers.IO).produce(Dispatchers.Main) {
            result.collect { homeResult ->
                when (homeResult) {
                    is HomeResult.Nothing -> Unit
                    is HomeResult.GetWeatherDetailsResult ->
                        state.weatherDetailsByLocation.value = homeResult.result
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun effects(): ReceiveChannel<HomeViewEffect> {
        return CoroutineScope(Dispatchers.IO).produce(Dispatchers.Main, Channel.CONFLATED) {
            result.collect { homeResult ->
                when (homeResult) {
                    is HomeResult.Nothing -> send(HomeViewEffect.Nothing)
                    is HomeResult.GetWeatherDetailsResult -> send(HomeViewEffect.ShowToast(null))
                }
            }
        }
    }

}