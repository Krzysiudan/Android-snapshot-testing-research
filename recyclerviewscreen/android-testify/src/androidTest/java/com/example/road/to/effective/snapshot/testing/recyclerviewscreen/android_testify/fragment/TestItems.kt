package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.fragment

import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.fragmentscenario.FragmentConfigItem
import  com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R

enum class HappyPathTestItem(val item: FragmentConfigItem) {
    PORTRAIT(
        FragmentConfigItem(
            locale = "en",
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
            orientation = Orientation.PORTRAIT
        ),
    ),
    PORTRAIT_NIGHT(
        FragmentConfigItem(
            locale = "en",
            uiMode = UiMode.NIGHT,
            fontSize = FontSize.NORMAL,
            orientation = Orientation.PORTRAIT
        ),
    ),
}

enum class UnhappyPathTestItem(val item: FragmentConfigItem) {
    CUSTOM_THEME_DAY(
        FragmentConfigItem(
            locale = "en",
            theme = R.style.Theme_Custom,
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
            orientation = Orientation.PORTRAIT
        ),
    ),
    HUGE_FONT(
        FragmentConfigItem(
            locale = "en",
            uiMode = UiMode.DAY,
            fontSize = FontSize.HUGE,
            orientation = Orientation.PORTRAIT
        ),
    ),
}
