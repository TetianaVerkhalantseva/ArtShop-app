package com.example.oblig3_verkhalantseva.presentation.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oblig3_verkhalantseva.R
import com.example.oblig3_verkhalantseva.presentation.category.data.CategoriesWithPhotosUI
import com.example.oblig3_verkhalantseva.presentation.ui.LazyColumnWithCategoriesPhotos
import com.example.oblig3_verkhalantseva.presentation.ui.MainTopAppBar
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CategoriesRoute(
    navigateToPhotosScreen: (Int) -> Unit,
    navigateBack: () -> Unit
) {
    val viewModel: CategoriesViewModel = viewModel()

    viewModel.getCategoriesWithPhotos()

    CategoriesScreen(
        categoriesWithPhotos = viewModel.categoriesWithPhotos,
        navigateToPhotosScreen = navigateToPhotosScreen,
        navigateBack = navigateBack
    )
}

@Composable
fun CategoriesScreen(
    categoriesWithPhotos: StateFlow<List<CategoriesWithPhotosUI>?>,
    navigateToPhotosScreen: (Int) -> Unit,
    navigateBack: () -> Unit
) {
    val categoriesWithPhotosState = categoriesWithPhotos.collectAsStateWithLifecycle()

    Column {
        MainTopAppBar(
            title = stringResource(id = R.string.choose_category),
            onNavigationIconClick = navigateBack
        )
        if (categoriesWithPhotosState.value != null) {
            LazyColumnWithCategoriesPhotos(
                categoriesWithPhotos = categoriesWithPhotosState.value!!,
                navigateToPhotosScreen = navigateToPhotosScreen,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                itemModifier = Modifier
                    .padding(vertical = 8.dp)
            )
        }
    }
}