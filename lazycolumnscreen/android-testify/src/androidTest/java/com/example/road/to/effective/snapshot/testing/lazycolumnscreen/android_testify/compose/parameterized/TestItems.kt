package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.compose.parameterized

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.R
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkItem
import sergio.sastre.uitesting.utils.activityscenario.ComposableConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

enum class HappyPathTestItem(val item: ComposableConfigItem) {
    PORTRAIT(
        ComposableConfigItem(
            locale = "en",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
            displaySize = DisplaySize.NORMAL,
        ),
    ),
}

enum class UnhappyPathTestItem(val item: ComposableConfigItem) {
    HUGE_FONT(
        ComposableConfigItem(
            locale = "en",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.DAY,
            fontSize = FontSize.HUGE,
            displaySize = DisplaySize.NORMAL,
        ),
    ),
}

internal val coffeeDrink =
    CoffeeDrinkItem(
        id = 1L,
        name = "Americano",
        imageUrl = R.drawable.americano_small,
        description =
        """
        Americano is a type of coffee drink prepared by diluting an espresso with hot water,
        giving it a similar strength to, but different flavour from, traditionally brewed coffee.
        """.trimIndent(),
        ingredients = "Espresso, Water",
        isFavourite = false
    )
