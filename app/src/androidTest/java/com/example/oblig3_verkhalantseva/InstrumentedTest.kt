package com.example.oblig3_verkhalantseva

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.oblig3_verkhalantseva.data.PhotosDB
import com.example.oblig3_verkhalantseva.navigation.PhotosNavHost
import com.example.oblig3_verkhalantseva.presentation.artists.navigation.ARTISTS_ROUTE
import com.example.oblig3_verkhalantseva.presentation.main.navigation.MAIN_ROUTE
import com.example.oblig3_verkhalantseva.presentation.photos.navigation.PHOTOS_ROUTE_WITH_IDS
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.FrameType
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.FrameWidth
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.PhotoSize
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.navigation.SELECTED_PHOTO_ROUTE_WITH_ID
import com.example.oblig3_verkhalantseva.ui.theme.Oblig3_VerkhalantsevaTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context
    private lateinit var navHostController: TestNavHostController

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.setContent {
            Oblig3_VerkhalantsevaTheme {
                navHostController = TestNavHostController(LocalContext.current)
                navHostController.navigatorProvider.addNavigator(ComposeNavigator())
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PhotosNavHost(navHostController)
                }
            }
        }
    }

    @Test
    fun checkStartDestination() {
        navHostController.assertCurrentRouteName(MAIN_ROUTE)
    }

    @Test
    fun checkArtistsDestination() {
        composeTestRule.onNodeWithStringId(context, R.string.button_artists).performClick()
        navHostController.assertCurrentRouteName(ARTISTS_ROUTE)
    }

    @Test
    fun checkPhotosDestination() {
        composeTestRule.onNodeWithStringId(context, R.string.button_artists).performClick()
        composeTestRule.onNodeWithTagStringId(context, R.string.lazyPhotoInfoItemTagWithIndex, 0)
            .performClick()
        navHostController.assertCurrentRouteName(PHOTOS_ROUTE_WITH_IDS)
    }


    @Test
    fun checkSelectedPhotoDestination() {
        composeTestRule.onNodeWithStringId(context, R.string.button_artists).performClick()
        composeTestRule.onNodeWithTagStringId(context, R.string.lazyPhotoInfoItemTagWithIndex, 0)
            .performClick()
        composeTestRule.onNodeWithTagStringId(context, R.string.lazyPhotoItemTagWithIndex, 0)
            .performClick()
        navHostController.assertCurrentRouteName(SELECTED_PHOTO_ROUTE_WITH_ID)
    }


    @Test
    fun checkSelectedPhotoToMainDestination() {
        composeTestRule.onNodeWithStringId(context, R.string.button_artists).performClick()
        composeTestRule.onNodeWithTagStringId(context, R.string.lazyPhotoInfoItemTagWithIndex, 0)
            .performClick()
        composeTestRule.onNodeWithTagStringId(context, R.string.lazyPhotoItemTagWithIndex, 0)
            .performClick()
        composeTestRule.onNodeWithStringId(context, R.string.button_go_to_home)
            .performClick()
        navHostController.assertCurrentRouteName(MAIN_ROUTE)
    }


    @Test
    fun checkPriceCountDestination() {
        //#1 Default selections
        val artistsIndex = 0
        val photosIndex = 1
        composeTestRule.onNodeWithStringId(context, R.string.button_artists).performClick()
        composeTestRule.onNodeWithTagStringId(
            context,
            R.string.lazyPhotoInfoItemTagWithIndex,
            artistsIndex
        )
            .performClick()
        composeTestRule.onNodeWithTagStringId(
            context,
            R.string.lazyPhotoItemTagWithIndex,
            photosIndex
        )
            .performClick()

        val photos = PhotosDB().getAllPhotos()

        var basicPrice = 0F
        var photoSize = PhotoSize.Small
        var frameType = FrameType.Wood
        var frameWidth = FrameWidth.Small
        photos.forEach { photo ->
            if (photo.artist.id == artistsIndex && photo.id == photosIndex.toLong()) {
                basicPrice = photo.price
                return@forEach
            }
        }

        var finalPrice = basicPrice + photoSize.price + frameWidth.price + frameType.price
        composeTestRule.onNodeWithTagAssertEquals(
            context = context,
            tag = R.string.calculatedSelectedPhotoPriceTag,
            expectedResult = context.getString(R.string.calculated_price, finalPrice)
        )


        //#2 Medium selections
        photoSize = PhotoSize.Medium
        frameType = FrameType.Plastic
        frameWidth = FrameWidth.Medium
        composeTestRule.onNodeWithTagStringId(
            context, R.string.photoSizeTag, context.getString(photoSize.nameResource)
        ).performClick()

        composeTestRule.onNodeWithTagStringId(
            context, R.string.frameTypeTag, context.getString(frameType.nameResource)
        ).performClick()

        composeTestRule.onNodeWithTagStringId(
            context, R.string.frameWidthTag, context.getString(frameWidth.nameResource)
        ).performClick()

        finalPrice = basicPrice + photoSize.price + frameWidth.price + frameType.price
        composeTestRule.onNodeWithTagAssertEquals(
            context = context,
            tag = R.string.calculatedSelectedPhotoPriceTag,
            expectedResult = context.getString(R.string.calculated_price, finalPrice)
        )

        //#3 Different selections
        photoSize = PhotoSize.Small
        frameType = FrameType.Metal
        frameWidth = FrameWidth.Large
        composeTestRule.onNodeWithTagStringId(
            context, R.string.photoSizeTag, context.getString(photoSize.nameResource)
        ).performClick()

        composeTestRule.onNodeWithTagStringId(
            context, R.string.frameTypeTag, context.getString(frameType.nameResource)
        ).performClick()

        composeTestRule.onNodeWithTagStringId(
            context, R.string.frameWidthTag, context.getString(frameWidth.nameResource)
        ).performClick()

        finalPrice = basicPrice + photoSize.price + frameWidth.price + frameType.price
        composeTestRule.onNodeWithTagAssertEquals(
            context = context,
            tag = R.string.calculatedSelectedPhotoPriceTag,
            expectedResult = context.getString(R.string.calculated_price, finalPrice)
        )


    }


}
