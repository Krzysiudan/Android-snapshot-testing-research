package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.parameterized

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksScreenPreview
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.PhoneOrientation
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.setDisplaySize
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.setPhoneOrientation
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:paparazzi:recordPaparazziDebug --tests '*Composable*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:paparazzi:verifyPaparazziDebug --tests '*Composable*'
 */

/**
 * Example of Parameterized test with Parameterized Runner.
 *
 * Unlike TestParameterInjector, the testItem is used in all @Tests (the test methods do not admit
 * arguments).
 */
@RunWith(Parameterized::class)
class CoffeeDrinkListComposableParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                nightMode = testItem.item.nightMode,
                fontScale = testItem.item.fontScale,
                locale = testItem.item.locale,
                orientation = testItem.item.phoneOrientation.toPaparazzi()
            )
        )


    @Test
    fun snapComposable() {
        paparazzi.context.setDisplaySize(testItem.item.displaySize)

        paparazzi.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized") {
                CoffeeDrinksScreenPreview()
        }
    }
}

@RunWith(Parameterized::class)
class CoffeeDrinkListComposableParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            nightMode = testItem.item.nightMode,
            fontScale = testItem.item.fontScale,
            locale = testItem.item.locale,
            orientation = testItem.item.phoneOrientation.toPaparazzi(),
            screenHeight = if(testItem.item.phoneOrientation == PhoneOrientation.PORTRAIT) DeviceConfig.PIXEL_5.screenHeight else DeviceConfig.PIXEL_5.screenWidth,
            screenWidth = if(testItem.item.phoneOrientation == PhoneOrientation.PORTRAIT) DeviceConfig.PIXEL_5.screenWidth else DeviceConfig.PIXEL_5.screenHeight,
        )
    )

    @Test
    fun snapComposable() {
        paparazzi.context.setDisplaySize(testItem.item.displaySize)

        paparazzi.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized") {
            AppTheme {
                CoffeeDrinksScreenPreview()
            }
        }
    }
}
