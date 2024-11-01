package com.example.oblig3_verkhalantseva.presentation.main.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.oblig3_verkhalantseva.navigation.GENERAL_ROUTE
import com.example.oblig3_verkhalantseva.presentation.main.MainRoute

const val MAIN_ROUTE = "main_route"

fun NavController.navigateToMain(navOptions: NavOptions? = null) =
    navigate(MAIN_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.mainScreen(
    navHostController: NavHostController,
    navigateToArtistsScreen: () -> Unit,
    navigateToCategoriesScreen: () -> Unit,
    navigateToPaymentScreen: () -> Unit
) {
    composable(
        route = MAIN_ROUTE
    ) {
        val navigationGraphEntry = remember(it) {
            navHostController.getBackStackEntry(GENERAL_ROUTE)
        }
        MainRoute(
            navigateToArtistsScreen = navigateToArtistsScreen,
            navigateToCategoriesScreen = navigateToCategoriesScreen,
            navigateToPaymentScreen = navigateToPaymentScreen,
            backStackEntry = navigationGraphEntry
        )
    }
}