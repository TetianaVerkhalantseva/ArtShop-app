package com.example.oblig3_verkhalantseva.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.oblig3_verkhalantseva.presentation.artists.navigation.artistsScreen
import com.example.oblig3_verkhalantseva.presentation.artists.navigation.navigateToArtists
import com.example.oblig3_verkhalantseva.presentation.category.navigation.categoriesScreen
import com.example.oblig3_verkhalantseva.presentation.category.navigation.navigateToCategories
import com.example.oblig3_verkhalantseva.presentation.main.navigation.MAIN_ROUTE
import com.example.oblig3_verkhalantseva.presentation.main.navigation.mainScreen
import com.example.oblig3_verkhalantseva.presentation.main.navigation.navigateToMain
import com.example.oblig3_verkhalantseva.presentation.payment.navigation.navigateToPayment
import com.example.oblig3_verkhalantseva.presentation.payment.navigation.paymentScreen
import com.example.oblig3_verkhalantseva.presentation.photos.navigation.IdType
import com.example.oblig3_verkhalantseva.presentation.photos.navigation.navigateToPhotos
import com.example.oblig3_verkhalantseva.presentation.photos.navigation.photosScreen
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.navigation.navigateToSelectedPhoto
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.navigation.selectedPhotoScreen

const val GENERAL_ROUTE = "general_route"

@Composable
fun PhotosNavHost(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = MAIN_ROUTE,
        route = GENERAL_ROUTE
    ) {
        mainScreen(
            navHostController = navController,
            navigateToArtistsScreen = { navController.navigateToArtists() },
            navigateToCategoriesScreen = { navController.navigateToCategories() },
            navigateToPaymentScreen = { navController.navigateToPayment() },
        )
        artistsScreen(
            navigateToPhotosScreen = {
                navController.navigateToPhotos(
                    id = it,
                    idType = IdType.ARTIST.id
                )
            },
            navigateBack = { navController.popBackStack() }
        )
        categoriesScreen(
            navigateToPhotosScreen = {
                navController.navigateToPhotos(
                    id = it,
                    idType = IdType.CATEGORY.id
                )
            },
            navigateBack = { navController.popBackStack() }
        )
        photosScreen(
            navigateToSelectedPhotoScreen = { navController.navigateToSelectedPhoto(it) },
            navigateBack = { navController.popBackStack() }
        )
        selectedPhotoScreen(
            navHostController = navController,
            navigateToMainScreen = { navController.navigateToMain() },
            navigateBack = { navController.popBackStack() }
        )
        paymentScreen(
            navigateToMainScreen = { navController.navigateToMain() },
            navigateBack = { navController.popBackStack() },
            navHostController = navController
        )
    }
}