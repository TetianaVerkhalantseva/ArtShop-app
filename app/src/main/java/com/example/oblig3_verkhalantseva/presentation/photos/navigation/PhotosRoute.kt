package com.example.oblig3_verkhalantseva.presentation.photos.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.oblig3_verkhalantseva.presentation.photos.PhotosRoute


const val PHOTOS_ROUTE = "photos_route"
const val PHOTOS_ROUTE_WITH_IDS = "photos_route/{id}/{idType}"

fun NavController.navigateToPhotos(
    navOptions: NavOptions? = null,
    id: Int,
    idType: Int
) =
    navigate(PHOTOS_ROUTE.plus("/${id}").plus("/${idType}"), navOptions = navOptions)



fun NavGraphBuilder.photosScreen(
    navigateToSelectedPhotoScreen : (Long) -> Unit,
    navigateBack: () -> Unit
) {
    composable(
        route = PHOTOS_ROUTE_WITH_IDS
    ) { backStackEntry ->
        PhotosRoute(
            id = backStackEntry.arguments?.getString(ArgumentsKeys.ID.key)?.toInt(),
            idType = backStackEntry.arguments?.getString(ArgumentsKeys.ID_TYPE.key)?.toInt(),
            navigateToSelectedPhoto = navigateToSelectedPhotoScreen,
            navigateBack = navigateBack
        )
    }
}

enum class IdType(val id: Int) {
    ARTIST(0), CATEGORY(1)
}

enum class ArgumentsKeys(val key: String) {
    ID("id"), ID_TYPE("idType")
}