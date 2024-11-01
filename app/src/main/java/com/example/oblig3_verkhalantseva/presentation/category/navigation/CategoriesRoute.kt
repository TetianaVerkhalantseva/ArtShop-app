package com.example.oblig3_verkhalantseva.presentation.category.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.oblig3_verkhalantseva.presentation.artists.ArtistsRoute
import com.example.oblig3_verkhalantseva.presentation.artists.navigation.ARTISTS_ROUTE
import com.example.oblig3_verkhalantseva.presentation.category.CategoriesRoute

const val CATEGORIES_ROUTE = "categories_route"

fun NavController.navigateToCategories(navOptions: NavOptions? = null) =
    navigate(CATEGORIES_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.categoriesScreen(
    navigateToPhotosScreen: (Int) -> Unit,
    navigateBack: () -> Unit
) {

    composable(
        route = CATEGORIES_ROUTE
    ) {
        CategoriesRoute(
            navigateToPhotosScreen,
            navigateBack = navigateBack
        )
    }
}