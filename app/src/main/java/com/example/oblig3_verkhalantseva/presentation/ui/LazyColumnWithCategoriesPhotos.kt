package com.example.oblig3_verkhalantseva.presentation.ui

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.oblig3_verkhalantseva.presentation.category.data.CategoriesWithPhotosUI

@Composable
fun LazyColumnWithCategoriesPhotos(
    categoriesWithPhotos: List<CategoriesWithPhotosUI>,
    navigateToPhotosScreen: (Int) -> Unit,
    modifier: Modifier = Modifier,
    itemModifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(categoriesWithPhotos) { categoryWithPhotos ->
            LazyPhotoInfoItem(
                modifier = itemModifier,
                startPhoto = categoryWithPhotos.category.categoryImage,
                title = stringResource(id = categoryWithPhotos.category.nameRes),
                amountOfPhotos = categoryWithPhotos.photos.size,
                mostExpensivePhotoName =
                if (categoryWithPhotos.mostExpensivePhotosTitle != null)
                    stringResource(id = categoryWithPhotos.mostExpensivePhotosTitle)
                else "",
                mostExpensivePhotoPrice = categoryWithPhotos.mostExpensivePhotosPrice,
                onClick = {

                    Log.e(
                        "WatchingSomeStuff",
                        "Category clicked = ${categoryWithPhotos.category.id}"
                    )
                    navigateToPhotosScreen(categoryWithPhotos.category.id)
                }
            )
        }
    }
}