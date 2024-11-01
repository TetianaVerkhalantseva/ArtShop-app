package com.example.oblig3_verkhalantseva.presentation.photos

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oblig3_verkhalantseva.R
import com.example.oblig3_verkhalantseva.data.model.Photo
import com.example.oblig3_verkhalantseva.presentation.ui.MainTopAppBar


@Composable
internal fun PhotosRoute(
    id: Int?,
    idType: Int?,
    navigateToSelectedPhoto: (Long) -> Unit,
    navigateBack: () -> Unit
) {
    val viewModel: PhotosViewModel = viewModel()

    val photosUIState by viewModel.photosUIState.collectAsStateWithLifecycle()
    val titleState by viewModel.categoryName.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getPhotos(id = id, idType = idType)
    }

    PhotosScreen(
        title = titleState,
        photosUIState = photosUIState,
        navigateToSelectedPhoto = navigateToSelectedPhoto,
        navigateBack = navigateBack
    )
}

@Composable
fun PhotosScreen(
    title: Int?,
    photosUIState: PhotosState,
    navigateToSelectedPhoto: (Long) -> Unit,
    navigateBack: () -> Unit
) {
    Column {
        MainTopAppBar(
            title =
            if (title != null) stringResource(id = title)
            else "",
            onNavigationIconClick = navigateBack
        )
        when (photosUIState) {
            is PhotosState.Empty -> {}
            is PhotosState.ErrorNoId -> {
                Text(text = stringResource(id = photosUIState.resourceMessage))
            }

            is PhotosState.Photos -> {
                LazyPhotosGrid(
                    photos = photosUIState.photos,
                    navigateToSelectedPhoto = navigateToSelectedPhoto
                )
            }
        }
    }
}

@Composable
fun LazyPhotosGrid(
    photos: List<Photo>,
    navigateToSelectedPhoto: (Long) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(photos) { photo ->
            Card(
                modifier = Modifier
                    .testTag(stringResource(id = R.string.lazyPhotoItemTagWithIndex, photo.id)),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = stringResource(id = photo.titleResId)
                )
                Image(
                    modifier = Modifier
                        .size(200.dp)
                        .clickable {
                            navigateToSelectedPhoto(photo.id)
                        },
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = photo.imageResId),
                    contentDescription = null
                )
            }
        }
    }
}