package com.example.oblig3_verkhalantseva.presentation.selectedPhoto.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.oblig3_verkhalantseva.navigation.GENERAL_ROUTE
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.SelectedPhotoRoute

const val SELECTED_PHOTO_ROUTE = "selected_photo_route"
const val SELECTED_PHOTO_ROUTE_WITH_ID = "selected_photo_route/{id}"

fun NavController.navigateToSelectedPhoto(photoId: Long, navOptions: NavOptions? = null) =
    navigate(SELECTED_PHOTO_ROUTE.plus("/$photoId"), navOptions = navOptions)

fun NavGraphBuilder.selectedPhotoScreen(
    navHostController: NavHostController,
    navigateToMainScreen: () -> Unit,
    navigateBack: () -> Unit
) {
    composable(
        route = SELECTED_PHOTO_ROUTE_WITH_ID
    ) { backStack ->
        val navigationGraphEntry = remember(backStack) {
            navHostController.getBackStackEntry(GENERAL_ROUTE)
        }
        SelectedPhotoRoute(
            photoID = backStack.arguments?.getString(ArgumentKeys.ID.value)?.toLong(),
            backStack = navigationGraphEntry,
            navigateToMainScreen = navigateToMainScreen,
            navigateBack = navigateBack
        )
    }
}

enum class ArgumentKeys(val value: String) {
    ID("id")
}