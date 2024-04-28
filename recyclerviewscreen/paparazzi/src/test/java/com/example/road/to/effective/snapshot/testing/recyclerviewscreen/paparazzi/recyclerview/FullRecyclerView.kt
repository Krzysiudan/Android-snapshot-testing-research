package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_XL
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dataproviders.memorise.UserMemoriseProvider
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.PhoneOrientation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.setPhoneOrientation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.viewholder.parameterized.DeviceConfig
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.viewholder.parameterized.HappyPathTestItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.RecyclerViewAsyncDiffAdapter
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.RecyclerViewDiffUtilCallback
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.header.HeaderDelegate
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.header.HeaderItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.header.HeaderType.TRAINING
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseDelegate
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingDelegate
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Execute the command below to run only AccessibilityTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:paparazzi:recordPaparazziDebug --tests '*RecyclerView*'
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:paparazzi:verifyPaparazziDebug --tests '*RecyclerView*'
 */

@RunWith(Parameterized::class)
class RecyclerViewTest(
    private val testItem: FullRecyclerViewTestItem
){

    private val deviceConfig
        get() = testItem.config
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<FullRecyclerViewTestItem> =
            FullRecyclerViewTestItem.entries.toTypedArray()
    }

    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig = PIXEL_XL.copy(
                nightMode = deviceConfig.nightMode,
                locale = deviceConfig.locale,
                fontScale = deviceConfig.fontScale,
            ).setPhoneOrientation(deviceConfig.orientation),
            supportsRtl = true, // needed for "ar" locale,
            theme = "Theme.RoadToEffectiveSnapshotTesting",
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapFullRecyclerView() {
        // prepare the Items
        val memoriseItems: List<MemoriseItem> =
            UserMemoriseProvider(paparazzi.context).getMemorises().toMemoriseItems()

        val recyclerViewItems =
            listOf(HeaderItem(TRAINING), wordsInSomeLangsTrainingItem) + memoriseItems

        // prepare the Adapter
        val rvAdapter =
            RecyclerViewAsyncDiffAdapter(
                RecyclerViewDiffUtilCallback(),
                HeaderDelegate(),
                TrainingDelegate(null),
                MemoriseDelegate(null)
            )

        // Prepare the RecyclerView
        val constraintLayout: View = paparazzi.inflate(R.layout.recycler_view_fragment)
        (constraintLayout.findViewById<View>(R.id.memoriseList) as RecyclerView)
            .apply { adapter = rvAdapter }
            .apply { rvAdapter.items = recyclerViewItems }

        paparazzi.snapshot(
            name = "RecyclerView_${testItem.name}_Parameterized",
            offsetMillis = 3_000L,
            view = constraintLayout,
        )
    }
}

private val wordsInSomeLangsTrainingItem = TrainingItem(
    trainingByLang = mapOf(
        Language.English to translations(3),
        Language.Russian to translations(5),
        Language.German to translations(1)
    ),
    activeLangs = setOf(Language.Russian, Language.German)
)

private fun translations(amount: Int): List<Translation> {
    val translation = translation()
    return mutableListOf<Translation>().apply { repeat(amount) { add(translation) } }
}

private fun translation(): Translation =
    Translation(
        "word",
        setOf(IntRange(0, 4)),
        Language.English,
        Language.English
    )

private fun List<Memorise>.toMemoriseItems(): List<MemoriseItem> =
    mapIndexed { index, memorise ->
        MemoriseItem(
            memorise = memorise,
            rightAligned = (index % 2 != 0),
            currentTime = 0,
        )
    }

enum class FullRecyclerViewTestItem(val config: DeviceConfig, val device: app.cash.paparazzi.DeviceConfig = PIXEL_XL){
    NORMAL(DeviceConfig()),
    NORMAL_NIGHT(DeviceConfig(nightMode = NightMode.NIGHT)),
    HUGE_FONT(DeviceConfig(fontScale = 1.5f)),
    SMALL_FONT(DeviceConfig(fontScale = 0.7f)),
    LANDSCAPE(DeviceConfig(orientation = PhoneOrientation.LANDSCAPE)),
    ARABIC(DeviceConfig(locale = "ar")),
    NORMAL_NEXUS_4(DeviceConfig()),
    NORMAL_NIGHT_NEXUS_4(DeviceConfig(nightMode = NightMode.NIGHT)),
}
