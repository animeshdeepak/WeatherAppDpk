package com.example.weatherappdpk.feature

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherappdpk.R
import com.example.weatherappdpk.data.screendata.BottomNavItem
import com.example.weatherappdpk.data.screendata.bottomNavItems
import com.example.weatherappdpk.feature.screen.HomeScreen
import com.example.weatherappdpk.feature.screen.SearchScreen
import com.example.weatherappdpk.feature.screen.SettingScreen
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    HandleEffects()
                    LaunchedEffect(key1 = viewModel.state.refreshPage) {
                        scope.launch(Dispatchers.IO) {
                            viewModel.intent.send(
                                HomeIntent.GetWeatherDetailsIntent(EndPointType.CURRENT, "New York")
                            )
                        }
                    }

                    Scaffold(bottomBar = {
                        NavigationBar(
                            containerColor = Color.Transparent
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route
                            bottomNavItems.forEach { screen ->
                                NavigationBarItem(
                                    selected = currentRoute == screen.route,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (currentRoute == screen.route) screen.filledIcon else screen.outlinedIcon,
                                            contentDescription = getString(screen.labelResourceId)
                                        )
                                    },
                                )

                            }
                        }
                    }) { innerPadding ->
                        NavHost(
                            navController,
                            startDestination = BottomNavItem.Home.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(BottomNavItem.Home.route) { HomeScreen(navController) }
                            composable(BottomNavItem.Search.route) { SearchScreen(navController) }
                            composable(BottomNavItem.Setting.route) { SettingScreen(navController) }
                        }
                    }
                }
            }
        }
    }
}