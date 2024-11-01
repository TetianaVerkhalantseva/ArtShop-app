package com.example.oblig3_verkhalantseva.presentation.selectedPhoto

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.example.oblig3_verkhalantseva.R
import com.example.oblig3_verkhalantseva.presentation.main.AddToCartProcessState
import com.example.oblig3_verkhalantseva.presentation.main.CartViewModel
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.data.PhotoWithSettingsUI
import com.example.oblig3_verkhalantseva.presentation.ui.CustomButton
import com.example.oblig3_verkhalantseva.presentation.ui.MainTopAppBar

@Composable
internal fun SelectedPhotoRoute(
    photoID: Long?,
    backStack: NavBackStackEntry,
    navigateToMainScreen: () -> Unit,
    navigateBack: () -> Unit
) {
    val viewModel: SelectedPhotoViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel(backStack)

    LaunchedEffect(key1 = Unit) {
        viewModel.findPhotoByID(photoID)
    }

    val photoSettingsState by viewModel.photoSizesUIState.collectAsStateWithLifecycle()
    val selectedPhotoSettingsState by viewModel.selectedPhotoSettingsState.collectAsStateWithLifecycle()

    val addToCartProcessState by cartViewModel.addToCartProcessState.collectAsStateWithLifecycle()

    SelectedPhotoScreen(
        photoSettingsState = photoSettingsState,
        selectedPhotoSettingsState = selectedPhotoSettingsState,
        selectPhotoSize = { viewModel.selectPhotoSize(it) },
        selectFrameType = { viewModel.selectFrameType(it) },
        selectFrameWidth = { viewModel.selectFrameWidth(it) },
        addPhotoToCart = { cartViewModel.addToCart(it) },
        navigateToMainScreen = navigateToMainScreen,
        addToCartProcessState = addToCartProcessState,
        clearAddToCartProcessState = { cartViewModel.clearAddToCartProcessState() },
        navigateBack = navigateBack

    )
}

@Composable
fun SelectedPhotoScreen(
    photoSettingsState: PhotoSettingsState,
    selectedPhotoSettingsState: SelectedPhotoState,
    selectPhotoSize: (PhotoSize) -> Unit,
    selectFrameType: (FrameType) -> Unit,
    selectFrameWidth: (FrameWidth) -> Unit,
    addToCartProcessState: AddToCartProcessState,
    addPhotoToCart: (PhotoWithSettingsUI) -> Unit,
    navigateToMainScreen: () -> Unit,
    clearAddToCartProcessState: () -> Unit,
    navigateBack: () -> Unit
) {

    (addToCartProcessState as? AddToCartProcessState.Finished)?.let {
        clearAddToCartProcessState()
        navigateToMainScreen()
    }

    (selectedPhotoSettingsState as? SelectedPhotoState.SelectedPhotoSettings)?.let { selectedPhotoState ->
        val photoSize =
            getPhotoSize(selectedPhotoState.selectedPhotoWithSettingsState.selectedPhotoSize)
        val frameWidth =
            getFrameWidth(selectedPhotoState.selectedPhotoWithSettingsState.selectedFrameWidth)
        val frameColor =
            getFrameColor(selectedPhotoState.selectedPhotoWithSettingsState.selectedFrameType)

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            MainTopAppBar(
                title = stringResource(id = R.string.selected_photo),
                onNavigationIconClick = navigateBack
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 2.dp, end = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = selectedPhotoState.selectedPhotoWithSettingsState.selectedPhoto.titleResId),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Box(
                    modifier = Modifier.size(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(photoSize + frameWidth),
                        contentAlignment = Alignment.Center
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(colorResource(id = frameColor))
                        )
                        Image(
                            modifier = Modifier
                                .size(photoSize),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = selectedPhotoState.selectedPhotoWithSettingsState.selectedPhoto.imageResId),
                            contentDescription = null
                        )
                    }
                }
                Text(
                    text = stringResource(
                        id = R.string.header_artist,
                        stringResource(id = selectedPhotoState.selectedPhotoWithSettingsState.selectedPhoto.artist.name)
                    ),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                if (photoSettingsState is PhotoSettingsState.PhotoSettings) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 2.dp,
                                shape = MaterialTheme.shapes.large,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            .padding(start = 2.dp, end = 2.dp, top = 2.dp, bottom = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.frame_type),
                                        style = MaterialTheme.typography.labelSmall,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                    photoSettingsState.frameTypes.forEach {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            RadioButton(
                                                modifier = Modifier
                                                    .testTag(
                                                        stringResource(
                                                            id = R.string.frameTypeTag,
                                                            stringResource(
                                                                id = it.nameResource
                                                            )
                                                        )
                                                    ),
                                                selected = it == selectedPhotoState.selectedPhotoWithSettingsState.selectedFrameType,
                                                onClick = { selectFrameType(it) }
                                            )
                                            Text(text = stringResource(id = it.nameResource))
                                        }
                                    }
                                }
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.frame_width),
                                        style = MaterialTheme.typography.labelSmall,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                    photoSettingsState.frameWidths.forEach {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            RadioButton(
                                                modifier = Modifier
                                                    .testTag(
                                                        stringResource(
                                                            id = R.string.frameWidthTag,
                                                            stringResource(
                                                                id = it.nameResource
                                                            )
                                                        )
                                                    ),
                                                selected = it == selectedPhotoState.selectedPhotoWithSettingsState.selectedFrameWidth,
                                                onClick = { selectFrameWidth(it) }
                                            )
                                            Text(text = stringResource(id = it.nameResource))
                                        }
                                    }
                                }
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.photo_size),
                                        style = MaterialTheme.typography.labelSmall,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                    photoSettingsState.photoSizes.forEach {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            RadioButton(
                                                modifier = Modifier
                                                    .testTag(
                                                        stringResource(
                                                            id = R.string.photoSizeTag,
                                                            stringResource(
                                                                id = it.nameResource
                                                            )
                                                        )
                                                    ),
                                                selected = it == selectedPhotoState.selectedPhotoWithSettingsState.selectedPhotoSize,
                                                onClick = { selectPhotoSize(it) }
                                            )
                                            Text(text = stringResource(id = it.nameResource))
                                        }
                                    }
                                }
                            }
                            Row {
                                Text(
                                    modifier = Modifier
                                        .testTag(stringResource(id = R.string.calculatedSelectedPhotoPriceTag)),
                                    text = stringResource(
                                        id = R.string.calculated_price,
                                        selectedPhotoState.selectedPhotoWithSettingsState.totalPrice
                                    )
                                )
                            }
                            CustomButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp),
                                title = stringResource(id = R.string.button_add_to_cart),
                                onClick = {
                                    addPhotoToCart(selectedPhotoState.selectedPhotoWithSettingsState)
                                },
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        }
                    }
                    Spacer(Modifier.height(15.dp))
                    CustomButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        title = stringResource(id = R.string.button_go_to_home),
                        onClick = navigateToMainScreen,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    }
}

fun getFrameWidth(selectedFrameWidth: FrameWidth): Dp =
    when (selectedFrameWidth) {
        FrameWidth.Small -> 10.dp
        FrameWidth.Medium -> 15.dp
        FrameWidth.Large -> 20.dp
    }


fun getPhotoSize(selectedPhotoSize: PhotoSize): Dp =
    when (selectedPhotoSize) {
        PhotoSize.Small -> 150.dp
        PhotoSize.Medium -> 200.dp
        PhotoSize.Large -> 250.dp
    }


fun getFrameColor(selectedFrameType: FrameType): Int =
    when (selectedFrameType) {
        FrameType.Wood -> R.color.brown
        FrameType.Plastic -> R.color.plastic
        FrameType.Metal -> R.color.silver

    }



