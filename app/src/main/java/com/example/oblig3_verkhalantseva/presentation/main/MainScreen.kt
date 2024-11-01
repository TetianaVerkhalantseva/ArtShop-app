package com.example.oblig3_verkhalantseva.presentation.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.example.oblig3_verkhalantseva.R
import com.example.oblig3_verkhalantseva.presentation.main.data.CartPhoto
import com.example.oblig3_verkhalantseva.presentation.ui.CustomButton
import com.example.oblig3_verkhalantseva.presentation.ui.LazyColumnWithCartPhotos
import com.example.oblig3_verkhalantseva.presentation.ui.MainTopAppBar

@Composable
internal fun MainRoute(
    navigateToArtistsScreen: () -> Unit,
    navigateToCategoriesScreen: () -> Unit,
    navigateToPaymentScreen: () -> Unit,
    backStackEntry: NavBackStackEntry
) {
    val viewModel: MainViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel(backStackEntry)
    Log.e("WatchingSomeStuff", "MainRoute!")

    val cartPhotosState by cartViewModel.cart.collectAsStateWithLifecycle()

    MainScreen(
        cartPhotosState = cartPhotosState,
        onCartItemRemove = { cartViewModel.removeCartPhoto(it) },
        navigateToArtistsScreen = navigateToArtistsScreen,
        navigateToCategoriesScreen = navigateToCategoriesScreen,
        navigateToPaymentScreen = navigateToPaymentScreen
    )
}

@Composable
fun MainScreen(
    cartPhotosState: CartState,
    onCartItemRemove: (CartPhoto) -> Unit,
    navigateToArtistsScreen: () -> Unit,
    navigateToCategoriesScreen: () -> Unit,
    navigateToPaymentScreen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        MainTopAppBar(
            title = stringResource(id = R.string.art_dealer),
            navigationIcon = null
        )
        Column(
            modifier = Modifier.padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.select_photo_based_on),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(bottom = 25.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp, end = 5.dp),
                    title = stringResource(id = R.string.button_artists),
                    onClick = navigateToArtistsScreen,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                CustomButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp, end = 20.dp),
                    title = stringResource(id = R.string.button_category),
                    onClick = navigateToCategoriesScreen,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }

        ShowCartPanel(
            cartPhotosState = cartPhotosState,
            onCartItemRemove = onCartItemRemove,
            navigateToPaymentScreen = navigateToPaymentScreen
        )
    }
}

@Composable
fun ShowCartPanel(
    cartPhotosState: CartState,
    onCartItemRemove: (CartPhoto) -> Unit,
    navigateToPaymentScreen: () -> Unit
) {
    if (cartPhotosState is CartState.Cart && cartPhotosState.photos.isNotEmpty()) {
        CartPanel(
            cartPhotosState = cartPhotosState,
            onCartItemRemove = onCartItemRemove,
            navigateToPaymentScreen = navigateToPaymentScreen
        )
    }
}

@Composable
fun CartPanel(
    cartPhotosState: CartState.Cart,
    onCartItemRemove: (CartPhoto) -> Unit,
    navigateToPaymentScreen: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.my_cart),
                style = MaterialTheme.typography.displayLarge,
            )
        }
        Column(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
        ) {
            Text(
                text = stringResource(
                    id = R.string.number_of_photos_selected,
                    cartPhotosState.amount
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
            Text(
                text = stringResource(
                    id = R.string.total_price_incl_frame,
                    cartPhotosState.totalPrice
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }

        LazyColumnWithCartPhotos(
            photos = cartPhotosState.photos,
            onItemRemove = onCartItemRemove,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            itemsPadding = 8.dp,
            itemModifier = Modifier
        )

        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                title = stringResource(id = R.string.button_go_to_payment),
                onClick = navigateToPaymentScreen,
                containerColor = MaterialTheme.colorScheme.secondary,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}