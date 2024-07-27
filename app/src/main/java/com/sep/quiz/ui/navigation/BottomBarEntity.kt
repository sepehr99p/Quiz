package com.sep.quiz.ui.navigation

import android.util.Log
import com.sep.quiz.R

data class BottomBarEntity(
    val id: BottomBarType,
    val title: String,
    val destination: String,
)

enum class BottomBarType(
    val filledIconRes: Int,
    val outlinedIconRes: Int,
    val needTopBar: Boolean
) {
    CRYPTO(
        filledIconRes = R.drawable.ic_crypto_filled,
        outlinedIconRes = R.drawable.ic_crypto_outline,
        needTopBar = false
    ),
    WEATHER(
        filledIconRes = R.drawable.ic_weather_filled,
        outlinedIconRes = R.drawable.ic_weather_outline,
        needTopBar = false
    ),
    GAME(
        filledIconRes = R.drawable.ic_game_filled,
        outlinedIconRes = R.drawable.ic_game_filled,
        needTopBar = false
    ),
    SETTING(
        filledIconRes = R.drawable.ic_setting_filled,
        outlinedIconRes = R.drawable.ic_setting_outline,
        needTopBar = false
    ),
    OTHER(
        filledIconRes = R.drawable.ic_other_filled,
        outlinedIconRes = R.drawable.ic_other_outline,
        needTopBar = false
    );


    companion object {
        fun getIdType(name: String?): BottomBarType {
            if (name == null)
                return OTHER
            return try {
                BottomBarType.valueOf(name.uppercase())
            } catch (e: Exception) {
                Log.e(BottomBarType::class.simpleName, "Enum NOT FOUND For $name")
                OTHER
            }
        }
    }
}
