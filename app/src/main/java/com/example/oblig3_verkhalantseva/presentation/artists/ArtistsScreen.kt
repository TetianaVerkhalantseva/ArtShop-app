package com.example.oblig3_verkhalantseva.presentation.artists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oblig3_verkhalantseva.R
import com.example.oblig3_verkhalantseva.presentation.artists.data.ArtistsWithPhotosUI
import com.example.oblig3_verkhalantseva.presentation.ui.LazyColumnWithArtistsPhotos
import com.example.oblig3_verkhalantseva.presentation.ui.MainTopAppBar
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ArtistsRoute(
    navigateToPhotosScreen: (Int) -> Unit,
    navigateBack: () -> Unit
) {
    val viewModel: ArtistsViewModel = viewModel()

    viewModel.getArtistsWithPhotos()

    ArtistsScreen(
        artistsWithPhotos = viewModel.artistsWithPhotos,
        navigateToPhotosScreen = navigateToPhotosScreen,
        navigateBack = navigateBack
    )
}

@Composable
fun ArtistsScreen(
    artistsWithPhotos: StateFlow<List<ArtistsWithPhotosUI>?>,
    navigateToPhotosScreen: (Int) -> Unit,
    navigateBack: () -> Unit
) {
    val artistsWithPhotosState = artistsWithPhotos.collectAsStateWithLifecycle()

    Column {
        MainTopAppBar(
            title = stringResource(id = R.string.choose_artist),
            onNavigationIconClick = navigateBack
        )
        if (artistsWithPhotosState.value != null) {
            LazyColumnWithArtistsPhotos(
                artistsWithPhotos = artistsWithPhotosState.value!!,
                navigateToPhotosScreen = navigateToPhotosScreen,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                itemModifier = Modifier
                    .padding(vertical = 8.dp)
            )
        }
    }
}
