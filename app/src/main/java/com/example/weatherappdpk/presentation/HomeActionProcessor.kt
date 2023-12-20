package com.example.weatherappdpk.presentation

import android.util.Log
import com.example.weatherappdpk.domain.usecase.GetWeatherDetailsByLocationUseCase
import com.example.weatherappdpk.domain.usecase.Params
import com.example.weatherappdpk.helper.MviActionProcessor
import com.example.weatherappdpk.helper.onFailure
import com.example.weatherappdpk.helper.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import javax.inject.Inject

class HomeActionProcessor @Inject constructor(
    private val getWeatherDetailsByLocationUseCase: GetWeatherDetailsByLocationUseCase,
) : MviActionProcessor<HomeAction, HomeResult>() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun process(actions: ReceiveChannel<HomeAction>): ReceiveChannel<HomeResult> {
        return CoroutineScope(Dispatchers.IO).produce {
            actions.consumeEach {
                when (it) {
                    is HomeAction.NothingAction -> send(HomeResult.Nothing)
                    is HomeAction.GetWeatherDetailsAction -> {
                        getWeatherDetailsByLocationUseCase.invoke(
                            Params(
                                it.type,
                                it.location
                            )
                        ).onSuccess { result ->
                            Log.d("DPK", "process: name: ${result.name}, temp: ${result.temperature}")
                            send(HomeResult.GetWeatherDetailsResult(result))
                        }.onFailure { message ->
                            Log.d("DPK", message)
                        }
                    }
                }
            }
        }
    }
}