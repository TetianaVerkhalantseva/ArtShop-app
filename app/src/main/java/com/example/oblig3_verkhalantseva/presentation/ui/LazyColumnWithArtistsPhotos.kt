package com.example.oblig3_verkhalantseva.presentation.ui

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.oblig3_verkhalantseva.R
import com.example.oblig3_verkhalantseva.presentation.artists.data.ArtistsWithPhotosUI

@Composable
fun LazyColumnWithArtistsPhotos(
    artistsWithPhotos: List<ArtistsWithPhotosUI>,
    navigateToPhotosScreen: (Int) -> Unit,
    modifier: Modifier = Modifier,
    itemModifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(artistsWithPhotos) { artistWithPhotos ->
            LazyPhotoInfoItem(
                modifier = itemModifier
                    .testTag(
                        stringResource(
                            id = R.string.lazyPhotoInfoItemTagWithIndex,
                            artistWithPhotos.artist.id
                        )
                    ),
                startPhoto = artistWithPhotos.artist.imageResId,
                title = stringResource(id = artistWithPhotos.artist.name),
                amountOfPhotos = artistWithPhotos.photos.size,
                mostExpensivePhotoName =
                if (artistWithPhotos.mostExpensivePhotosTitle != null)
                    stringResource(id = artistWithPhotos.mostExpensivePhotosTitle)
                else "",
                mostExpensivePhotoPrice = artistWithPhotos.mostExpensivePhotosPrice,
                onClick = {

                    Log.e("WatchingSomeStuff", "Artist clicked = ${artistWithPhotos.artist.id}")
                    navigateToPhotosScreen(artistWithPhotos.artist.id)
                }
            )
        }
    }
}