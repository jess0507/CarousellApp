package com.jess.carousellnews

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jess.carousellnews.ui.list.ListScreen
import com.jess.data.test.FakeSource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testListScreen_displayContent() {
        val fakeNews = FakeSource.news

        // Set the Composable under test
        composeTestRule.setContent {
            ListScreen(isLoading = false, news = fakeNews)
        }

        // Assert that the title is displayed
        composeTestRule.onNodeWithText("Carousell News").assertIsDisplayed()
        composeTestRule.onNodeWithText("Southeast Asia-based mobile listings startup Carousell raises ${'$'}85M").assertIsDisplayed()

        // Assert that the dropdown menu is shown and working
        composeTestRule.onNodeWithContentDescription("Menu").performClick()
        composeTestRule.onNodeWithText("Recent").assertIsDisplayed()
        composeTestRule.onNodeWithText("Popular").assertIsDisplayed()

        // Test clicking "Popular" changes the sorting
        composeTestRule.onNodeWithText("Popular").performClick()
        composeTestRule.onNodeWithText("Carousell funding").assertIsDisplayed()
    }
}