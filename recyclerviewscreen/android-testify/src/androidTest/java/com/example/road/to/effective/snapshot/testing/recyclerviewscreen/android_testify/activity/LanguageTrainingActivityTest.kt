package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.activity

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingActivity
import com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import dev.testify.ScreenshotRule
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.TestifyConfiguration
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.assertSame
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule
import sergio.sastre.uitesting.utils.testrules.fontsize.FontSizeTestRule
import sergio.sastre.uitesting.utils.testrules.locale.InAppLocaleTestRule
import sergio.sastre.uitesting.utils.testrules.locale.SystemLocaleTestRule
import sergio.sastre.uitesting.utils.testrules.uiMode.UiModeTestRule
import java.util.Locale

/**
 * Execute the command below to run only ActivityTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :recyclerviewscreen:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class LanguageTrainingActivityHappyPathTest {

    @get:Rule(order = 0)
    val disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 2)
    val activityScreenshotRule =
        ScreenshotRule(
            configuration = TestifyConfiguration(
                exactness = 0.85f,
                orientation = SCREEN_ORIENTATION_PORTRAIT,
            ),
            activityClass = LanguageTrainingActivity::class.java,
        )

    @ScreenshotInstrumentation
    @HappyPath
    @ActivityTest
    @Test
    fun snapActivity() {
        activityScreenshotRule
            .assertSame(name = "LanguageTrainingActivity_HappyPath")
    }
}
