package com.example.oblig3_verkhalantseva

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import org.junit.Assert

fun ComposeContentTestRule.onNodeWithStringId(
    context: Context,
    @StringRes id: Int
): SemanticsNodeInteraction =
    onNodeWithText(context.getString(id), useUnmergedTree = true)

fun ComposeContentTestRule.onNodeWithTagStringId(
    context: Context,
    @StringRes id: Int,
    argument: Any
): SemanticsNodeInteraction =
    onNodeWithTag(context.getString(id, argument), useUnmergedTree = true)


fun ComposeContentTestRule.onNodeWithTagAssertEquals(
    context: Context,
    @StringRes tag: Int,
    expectedResult: String
) {
    onNodeWithTag(context.getString(tag))
        .assertTextEquals(expectedResult)
}