package com.example.oblig3_verkhalantseva.presentation.artists.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.oblig3_verkhalantseva.presentation.artists.ArtistsRoute

const val ARTISTS_ROUTE = "artists_route"

fun NavController.navigateToArtists(navOptions: NavOptions? = null) =
    navigate(ARTISTS_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.artistsScreen(
    navigateToPhotosScreen: (Int) -> Unit,
    navigateBack: () -> Unit
) {

    composable(
        route = ARTISTS_ROUTE
    ) {
        ArtistsRoute(
            navigateToPhotosScreen,
            navigateBack = navigateBack
        )
    }
}