package com.example.oblig3_verkhalantseva.presentation.photos

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_verkhalantseva.R
import com.example.oblig3_verkhalantseva.data.PhotosDB
import com.example.oblig3_verkhalantseva.data.model.Category
import com.example.oblig3_verkhalantseva.data.model.Photo
import com.example.oblig3_verkhalantseva.presentation.photos.navigation.IdType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotosViewModel() : ViewModel() {
    private val photosDB = PhotosDB()

    private val _photosUIState: MutableStateFlow<PhotosState> = MutableStateFlow(PhotosState.Empty)
    val photosUIState = _photosUIState.asStateFlow()
    private val _categoryName: MutableStateFlow<Int?> = MutableStateFlow(null)
    val categoryName = _categoryName.asStateFlow()


    fun getPhotos(id: Int?, idType: Int?) {
        Log.e("WatchingSomeStuff", "id = $id, idType = $idType")
        viewModelScope.launch {
            if (id == null || idType == null) {
                _photosUIState.emit(PhotosState.ErrorNoId(R.string.something_went_wrong))
            } else {
                val photos = photosDB.getAllPhotos()
                when (idType) {
                    IdType.ARTIST.id -> {
                        photos.forEach {
                            if (it.artist.id == id) {
                                _categoryName.emit(it.artist.name)
                                return@forEach
                            }
                        }
                        Log.e("WatchingSomeStuff", "ARTIST ID")
                        _photosUIState.emit(
                            PhotosState.Photos(
                                photos =
                                photos.filter {
                                    it.artist.id == id
                                }
                            )
                        )
                    }

                    IdType.CATEGORY.id -> {
                        Category.entries.forEach {
                            if (id == it.id) _categoryName.emit(it.nameRes)
                        }
                        Log.e("WatchingSomeStuff", "ID CATEGORY")
                        _photosUIState.emit(
                            PhotosState.Photos(
                                photos =
                                photos.filter {
                                    it.category.id == id
                                }
                            )
                        )
                    }

                    else -> {
                        _photosUIState.emit(PhotosState.ErrorNoId(R.string.something_went_wrong))
                    }
                }
            }
        }
    }
}

sealed interface PhotosState {
    data object Empty : PhotosState
    data class ErrorNoId(@StringRes val resourceMessage: Int) : PhotosState
    data class Photos(val photos: List<Photo>) : PhotosState
}
