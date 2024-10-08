package com.sep.byteClub.data.model.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("hourly")
    val weatherData: WeatherDataDto
)
