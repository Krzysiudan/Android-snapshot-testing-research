package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.DisplaySize
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.PhoneOrientation
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.setDisplaySize
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.setPhoneOrientation
import org.junit.Rule
import org.junit.Test

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:paparazzi:recordPaparazziDebug --tests '*Composable*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:paparazzi:verifyPaparazziDebug --tests '*Composable*'
 */
class CoffeeDrinkAppBarComposableHappyPathTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig =
            DeviceConfig.PIXEL_5.copy(
                nightMode = NightMode.NOTNIGHT,
                fontScale = 1.0f,
                locale = "en",
            ).setPhoneOrientation(PhoneOrientation.PORTRAIT),
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapComposable() {
        paparazzi.context.setDisplaySize(DisplaySize.LARGER)

        paparazzi.snapshot(name = "CoffeeDrinkAppBar_Happy") {
            AppTheme {
                CoffeeDrinkAppBar()
            }
        }
    }
}

class CoffeeDrinkAppBarComposableUnhappyPathTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                nightMode = NightMode.NIGHT,
                fontScale = 1.3f,
                locale = "ar-rXB",
            ).setPhoneOrientation(PhoneOrientation.LANDSCAPE),
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapComposable() {
        paparazzi.context.setDisplaySize(DisplaySize.LARGER)

        paparazzi.snapshot(name = "CoffeeDrinkAppBar_Unhappy") {
            AppTheme {
                CoffeeDrinkAppBar()
            }
        }
    }
}
