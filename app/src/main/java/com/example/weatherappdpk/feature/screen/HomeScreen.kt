package com.example.weatherappdpk.feature.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherappdpk.R

@Composable
fun HomeScreen(
    navController: NavController,
) {
    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Cyan)
        ) {
            Text(
                text = "Country",
                color = Color.Black,
                fontSize = 50.sp,
                modifier = Modifier.padding(top = 100.dp)
            )
            Text(
                text = "Sunday 9:44 am",
                color = Color.Black,
                fontSize = 24.sp,
            )
            Image(
                painter = painterResource(id = R.drawable.ic_rainy),
                contentDescription = "monsoon image",
                modifier = Modifier.padding(top = 30.dp)
            )
            Text(
                text = "31.C",
                color = Color.Black,
                fontSize = 50.sp,
                modifier = Modifier.padding(top = 24.dp)
            )
            Text(
                text = "Sunny",
                color = Color.Black,
                fontSize = 24.sp,
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
            ) {
                WeatherInfo(
                    image = R.drawable.ic_wind,
                    name = "Wind",
                    info = "12kph",
                    modifier = Modifier
                )

                WeatherInfo(
                    image = R.drawable.ic_rainy,
                    name = "Precipitation",
                    info = "2%",
                    modifier = Modifier
                )

                WeatherInfo(
                    image = R.drawable.ic_snow,
                    name = "Humidity",
                    info = "83%",
                    modifier = Modifier
                )

            }
        }
    }
}

@Composable
fun WeatherInfo(
    @DrawableRes image: Int, name: String, info: String, modifier: Modifier
) {
    val configureName: String = name
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = name,
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = configureName,
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 5.dp)
        )
        Text(
            text = info,
            color = Color.Black,
            fontSize = 24.sp,
        )
    }
}