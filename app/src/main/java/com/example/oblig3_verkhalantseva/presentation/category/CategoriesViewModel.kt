package com.example.oblig3_verkhalantseva.presentation.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_verkhalantseva.data.PhotosDB
import com.example.oblig3_verkhalantseva.presentation.category.mapper.toUI
import com.example.oblig3_verkhalantseva.presentation.category.data.CategoriesWithPhotosUI
import com.example.oblig3_verkhalantseva.presentation.category.data.CategoryWithPhotos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel(): ViewModel() {

    private val photosDB = PhotosDB()

    private val _categoriesWithPhotos : MutableStateFlow<List<CategoriesWithPhotosUI>?> = MutableStateFlow(null)
    val categoriesWithPhotos: StateFlow<List<CategoriesWithPhotosUI>?> = _categoriesWithPhotos.asStateFlow()

    fun getCategoriesWithPhotos() {
        viewModelScope.launch {
            val photos = photosDB.getAllPhotos()
            val categoriesWithPhotos = mutableListOf<CategoryWithPhotos>()

            photos.forEach { photo ->
                val categoriesIndex = categoriesWithPhotos.indexOfFirst {
                    it.category.id == photo.category.id
                }

                if (categoriesIndex == -1) {
                    categoriesWithPhotos.add(
                        CategoryWithPhotos(
                            category = photo.category
                        ).apply {
                            addPhoto(photo.toUI())
                        }
                    )
                }
                else {
                    categoriesWithPhotos[categoriesIndex].addPhoto(photo.toUI())
                }

            }
            _categoriesWithPhotos.emit(categoriesWithPhotos.map { it.toUI() })
            Log.e("WatchingSomeStuff", "final artists with photos = $categoriesWithPhotos")

        }
    }
}