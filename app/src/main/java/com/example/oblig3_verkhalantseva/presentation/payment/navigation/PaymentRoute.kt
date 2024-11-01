package com.example.oblig3_verkhalantseva.presentation.payment.navigation


import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.oblig3_verkhalantseva.navigation.GENERAL_ROUTE
import com.example.oblig3_verkhalantseva.presentation.payment.PaymentRoute


const val PAYMENT_ROUTE = "payment_route"

fun NavController.navigateToPayment(navOptions: NavOptions? = null) =
    navigate(PAYMENT_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.paymentScreen(
    navHostController: NavHostController,
    navigateToMainScreen: () -> Unit,
    navigateBack: () -> Unit
) {
    composable(
        route = PAYMENT_ROUTE
    ) {
        val navigationGraphEntry = remember(it) {
            navHostController.getBackStackEntry(GENERAL_ROUTE)
        }
        PaymentRoute(
            navigateToMainScreen,
            backStackEntry = navigationGraphEntry,
            navigateBack = navigateBack
        )
    }
}