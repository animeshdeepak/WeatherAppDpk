package com.example.weatherappdpk.feature

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappdpk.data.screendata.items
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
                        homeViewEffect.message ?: "Something went wrong!"
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
                            NavigationBar {
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
                                        label = { Text(text = item.title) }
                                    )

                                }
                            }
                        }
                    ) {
                        Greeting("Android", modifier = Modifier.padding(paddingValues = it))
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Text(
        text = "Hello $name!",
        modifier = modifier
            .clickable {
                Toast.makeText(context, "text clicked", Toast.LENGTH_SHORT).show()
                scope.launch(Dispatchers.IO) {
                    viewModel.intent.send(
                        HomeIntent.GetWeatherDetailsIntent(EndPointType.CURRENT, "New York")
                    )
                }
            }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppDpkTheme {
        Greeting("Android")
    }
}