/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.material3

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.testutils.assertPixels
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SdkSuppress
import kotlin.math.roundToInt
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class DividerTest {

    @get:Rule
    val rule = createComposeRule()

    private val defaultThickness = 1.dp

    @Test
    fun horizontalDivider_DefaultSizes() {
        rule
            .setMaterialContentForSizeAssertions {
                Divider()
            }
            .assertHeightIsEqualTo(defaultThickness)
            .assertWidthIsEqualTo(rule.rootWidth())
    }

    @Test
    fun horizontalDivider_CustomSizes() {
        val thickness = 20.dp
        rule
            .setMaterialContentForSizeAssertions {
                Divider(thickness = thickness)
            }
            .assertWidthIsEqualTo(rule.rootWidth())
            .assertHeightIsEqualTo(thickness)
    }

    @Test
    fun verticalDivider_DefaultSizes() {
        rule
            .setMaterialContentForSizeAssertions {
                Divider(horizontal = false)
            }
            .assertHeightIsEqualTo(rule.rootHeight())
            .assertWidthIsEqualTo(defaultThickness)
    }

    @Test
    fun verticalDivider_CustomSizes() {
        val thickness = 20.dp
        rule
            .setMaterialContentForSizeAssertions {
                Divider(thickness = thickness, horizontal = false)
            }
            .assertWidthIsEqualTo(thickness)
            .assertHeightIsEqualTo(rule.rootHeight())
    }

    @Test
    fun divider_SizesWithIndent_DoesNotChanged() {
        val indent = 75.dp
        val thickness = 21.dp

        rule
            .setMaterialContentForSizeAssertions {
                Divider(modifier = Modifier.padding(start = indent), thickness = thickness)
            }
            .assertHeightIsEqualTo(thickness)
            .assertWidthIsEqualTo(rule.rootWidth())
    }

    @Test
    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
    fun divider_HairlineThickness() {
        val size = 5.dp
        val testTag = "testTag"
        var sizePx = 0
        var dividerColor = Color.Transparent
        rule.setContent {
            sizePx = with(LocalDensity.current) { size.toPx().roundToInt() }
            dividerColor =
                MaterialTheme.colorScheme.outlineVariant
            Box(modifier = Modifier.size(size).background(Color.Black)) {
                Divider(
                    modifier = Modifier.testTag(testTag).fillMaxWidth(),
                    thickness = Dp.Hairline
                )
            }
        }

        rule.onNodeWithTag(testTag).captureToImage().assertPixels(IntSize(sizePx, 1)) {
            dividerColor
        }
    }
}