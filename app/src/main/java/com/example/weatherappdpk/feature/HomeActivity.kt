package com.example.weatherappdpk.feature

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.weatherappdpk.R
import com.example.weatherappdpk.data.screendata.items
import com.example.weatherappdpk.feature.screen.HomeScreen
import com.example.weatherappdpk.helper.EndPointType
import com.example.weatherappdpk.helper.toastL
import com.example.weatherappdpk.helper.toastS
import com.example.weatherappdpk.presentation.HomeIntent
import com.example.weatherappdpk.presentation.HomeViewEffect
import com.example.weatherappdpk.ui.theme.WeatherAppDpkTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private val viewModel by viewModels<MainViewModel>()

    @Composable
    fun HandleEffects() {
        LaunchedEffect(key1 = Unit) {
            viewModel.effects().consumeEach { homeViewEffect ->
                when (homeViewEffect) {
                    is HomeViewEffect.Nothing -> Unit
                    is HomeViewEffect.ShowToast -> toastS(
                        homeViewEffect.message ?: getString(R.string.something_went_wrong)
                    )

                    is HomeViewEffect.Error -> toastL(homeViewEffect.message)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppDpkTheme {
                val scope = rememberCoroutineScope()
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HandleEffects()
                    LaunchedEffect(key1 = viewModel.state.refreshPage) {
                        scope.launch(Dispatchers.IO) {
                            viewModel.intent.send(
                                HomeIntent.GetWeatherDetailsIntent(EndPointType.CURRENT, "New York")
                            )
                        }
                    }

                    Scaffold(
                        bottomBar = {
                            NavigationBar(
                                containerColor = Color.Transparent
                            ) {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                            // navController.navigate(item.title) todo dpk
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                                contentDescription = item.title
                                            )
                                        },
                                    )

                                }
                            }
                        }
                    ) { paddingValues ->
                        HomeScreen()
                    }
                }
            }
        }
    }
}