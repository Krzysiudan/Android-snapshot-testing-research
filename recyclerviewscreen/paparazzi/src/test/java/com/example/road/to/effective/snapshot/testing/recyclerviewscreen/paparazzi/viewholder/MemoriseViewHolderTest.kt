package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.viewholder

import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.DisplaySize
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.setDisplaySize
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.setPhoneOrientation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.viewholder.parameterized.HappyPathTestItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Execute the command below to run only ViewHolderTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:paparazzi:recordPaparazziDebug --tests '*ViewHolder*'
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:paparazzi:verifyPaparazziDebug --tests '*ViewHolder*'
 */

/**
 * Example with ActivityScenarioForViewRule of AndroidUiTestingUtils
 */
@RunWith(Parameterized::class)
class MemoriseViewHolderHappyPathTest(
    private val testItem: HappyPathTestItem
) {

    private val deviceConfig
        get() = testItem.item.deviceConfig

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig =
        DeviceConfig.PIXEL_5.copy(
            nightMode = deviceConfig.nightMode,
            locale = deviceConfig.locale,
            fontScale = deviceConfig.fontScale,
        ).setPhoneOrientation(deviceConfig.orientation),
        theme = deviceConfig.theme,
        supportsRtl = true, // needed for "ar" locale
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
    )

    @Test
    fun snapViewHolder() {
        // Must be called inside the test: paparazzi.context is null before
        paparazzi.setDisplaySize(DisplaySize.NORMAL)

        val layout = paparazzi.inflate<View>(R.layout.memorise_row)
        val view = MemoriseViewHolder(
            container = layout,
            itemEventListener = null,
            animationDelay = 0L
        ).apply {
            bind(generateMemoriseItem(rightAligned = false, activity = paparazzi.context))
        }

        paparazzi.snapshot(
            view = view.itemView,
            name = "${testItem.name}_MemoriseView_Happy_Parametrized"
        )
    }
}
