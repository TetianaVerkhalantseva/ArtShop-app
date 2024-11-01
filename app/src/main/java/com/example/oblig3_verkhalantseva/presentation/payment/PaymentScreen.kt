package com.example.oblig3_verkhalantseva.presentation.payment

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.example.oblig3_verkhalantseva.R
import com.example.oblig3_verkhalantseva.extensions.fromDp
import com.example.oblig3_verkhalantseva.presentation.main.CartState
import com.example.oblig3_verkhalantseva.presentation.main.CartViewModel
import com.example.oblig3_verkhalantseva.presentation.main.data.CartPhoto
import com.example.oblig3_verkhalantseva.presentation.ui.CustomButton
import com.example.oblig3_verkhalantseva.presentation.ui.MainTopAppBar

@Composable
fun PaymentRoute(
    navigateToMainScreen: () -> Unit,
    navigateBack: () -> Unit,
    backStackEntry: NavBackStackEntry
) {
    val viewModel: PaymentViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel(backStackEntry)

    val isConfirmDialogVisibleState by viewModel.confirmDialogIsVisibleState.collectAsStateWithLifecycle()
    val cartState by cartViewModel.cart.collectAsStateWithLifecycle()

    PaymentScreen(
        cartState = cartState,
        isConfirmDialogVisibleState = isConfirmDialogVisibleState,
        setConfirmDialogVisibility = { viewModel.setConfirmDialogVisibility(it) },
        buyAllCartPhotos = { cartViewModel.buyAllPhotos() },
        navigateToMainScreen = navigateToMainScreen,
        navigateBack = navigateBack
    )
}

@Composable
fun PaymentScreen(
    cartState: CartState,
    buyAllCartPhotos: () -> Unit,
    isConfirmDialogVisibleState: Boolean,
    setConfirmDialogVisibility: (Boolean) -> Unit,
    navigateToMainScreen: () -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        MainTopAppBar(
            title = stringResource(id = R.string.payment),
            onNavigationIconClick = navigateBack
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            ShowPaymentPhotos(cartState)
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        shape = MaterialTheme.shapes.large,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = stringResource(id = R.string.payment_choice),
                            style = MaterialTheme.typography.displayMedium,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.card_no),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Text(
                            text = stringResource(id = R.string.expiration_date),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Text(
                            text = stringResource(id = R.string.cvc_no),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                    CustomButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        title = stringResource(id = R.string.button_pay),
                        onClick = { setConfirmDialogVisibility(true) },
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
        }

        ShowConfirmDialog(
            isConfirmDialogVisible = isConfirmDialogVisibleState,
            onConfirmClick = {
                setConfirmDialogVisibility(false)
                buyAllCartPhotos()
                navigateToMainScreen()
            },
            onDismiss = { setConfirmDialogVisibility(false) }
        )
    }
}

@Composable
fun ShowPaymentPhotos(cartState: CartState) {
    if (cartState is CartState.Cart && cartState.photos.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.onBackground
                )
                .padding(10.dp)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 20.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = stringResource(id = R.string.summary_of_choice),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                )
                LazyColumnPaymentPhotos(
                    photos = cartState.photos,
                    modifier = Modifier,
                    itemModifier = Modifier,
                    itemsVerticalPadding = 20.dp
                )
                Text(
                    text = stringResource(
                        id = R.string.number_of_photos_selected,
                        cartState.amount
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                )
                Text(
                    text = stringResource(
                        id = R.string.total_price_incl_frame,
                        cartState.totalPrice
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun LazyColumnPaymentPhotos(
    photos: List<CartPhoto>,
    modifier: Modifier,
    itemModifier: Modifier,
    itemsVerticalPadding: Dp
) {
    val oneItemHeight = remember {
        mutableIntStateOf(0)
    }
    LazyColumn(
        modifier = modifier
            .height((oneItemHeight.intValue.fromDp().dp) * 2)
    ) {
        items(photos) { photo ->
            PaymentPhotoItem(
                photo = photo,
                modifier = itemModifier
                    .onSizeChanged {
                        if (oneItemHeight.intValue != it.height)
                            oneItemHeight.intValue = it.height

                    },
                itemsVerticalPadding = itemsVerticalPadding
            )
        }
    }
}

@Composable
fun PaymentPhotoItem(
    photo: CartPhoto,
    modifier: Modifier,
    itemsVerticalPadding: Dp
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = itemsVerticalPadding),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(
                id = R.string.photo_title_info,
                stringResource(id = photo.photo.titleResId)
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            text = stringResource(
                id = R.string.photo_size_info,
                stringResource(id = photo.photoSize.nameResource)
            ),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            text = stringResource(
                id = R.string.frame_type_info,
                stringResource(id = photo.frameType.nameResource)
            ),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            text = stringResource(id = R.string.frame_width_info, photo.frameWidth.width),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.photo_price, photo.photo.price),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            text = stringResource(
                id = R.string.photo_size_info,
                photo.photoSize.price
            ),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            text = stringResource(
                id = R.string.frame_price,
                photo.frameType.price + photo.frameWidth.price
            ),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            text = stringResource(id = R.string.total_price_incl_frame, photo.totalPrice),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}

@Composable
fun ShowConfirmDialog(
    isConfirmDialogVisible: Boolean,
    onConfirmClick: () -> Unit,
    onDismiss: () -> Unit
) {
    if (isConfirmDialogVisible) {
        ConfirmDialog(
            onConfirmClick = onConfirmClick,
            onDismiss = onDismiss
        )
    }
}

@Composable
fun ConfirmDialog(
    onConfirmClick: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(stringResource(id = R.string.payment_received))
        },
        containerColor = MaterialTheme.colorScheme.tertiary,
        textContentColor = MaterialTheme.colorScheme.onTertiary,
        confirmButton = {
            Button(
                onClick = onConfirmClick
            ) {
                Text(
                    text = stringResource(id = R.string.button_ok),
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    )

}


