package com.example.oblig3_verkhalantseva.presentation.selectedPhoto

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_verkhalantseva.R
import com.example.oblig3_verkhalantseva.data.PhotosDB
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.data.PhotoWithSettingsUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SelectedPhotoViewModel() : ViewModel() {

    private val photosDB = PhotosDB()
    private val photos = photosDB.getAllPhotos()


    private val _photoSizesUIState: MutableStateFlow<PhotoSettingsState> = MutableStateFlow(
        PhotoSettingsState.PhotoSettings(
            photoSizes = PhotoSize.entries,
            frameTypes = FrameType.entries,
            frameWidths = FrameWidth.entries
        )
    )
    val photoSizesUIState = _photoSizesUIState.asStateFlow()

    private val _selectedPhotoSettingsState: MutableStateFlow<SelectedPhotoState> =
        MutableStateFlow(SelectedPhotoState.Empty)
    val selectedPhotoSettingsState = _selectedPhotoSettingsState.asStateFlow()

    fun selectPhotoSize(photoSize: PhotoSize) {
        viewModelScope.launch {
            val value = _selectedPhotoSettingsState.value
            if (value is SelectedPhotoState.SelectedPhotoSettings) {
                _selectedPhotoSettingsState.emit(
                    SelectedPhotoState.SelectedPhotoSettings(
                        value.selectedPhotoWithSettingsState.copy(
                            selectedPhotoSize = photoSize
                        )
                    )
                )
            }
        }
    }

    fun selectFrameType(frameType: FrameType) {
        viewModelScope.launch {
            val value = _selectedPhotoSettingsState.value
            if (value is SelectedPhotoState.SelectedPhotoSettings) {
                _selectedPhotoSettingsState.emit(
                    SelectedPhotoState.SelectedPhotoSettings(
                        value.selectedPhotoWithSettingsState.copy(
                            selectedFrameType = frameType
                        )
                    )
                )
            }
        }
    }

    fun selectFrameWidth(frameWidth: FrameWidth) {
        viewModelScope.launch {
            val value = _selectedPhotoSettingsState.value
            if (value is SelectedPhotoState.SelectedPhotoSettings) {
                _selectedPhotoSettingsState.emit(
                    SelectedPhotoState.SelectedPhotoSettings(
                        value.selectedPhotoWithSettingsState.copy(
                            selectedFrameWidth = frameWidth
                        )
                    )
                )
            }
        }
    }

    fun findPhotoByID(photoID: Long?) {
        Log.e("WatchingSomeStuff", "Find photo by id")
        viewModelScope.launch {

            val value = _selectedPhotoSettingsState.value
            if (value is SelectedPhotoState.SelectedPhotoSettings) return@launch

            val selectedPhoto = photos.find { it.id == photoID }

            if (selectedPhoto == null) {
                _selectedPhotoSettingsState.emit(SelectedPhotoState.Error(R.string.something_went_wrong))
                return@launch
            }

            _selectedPhotoSettingsState.emit(
                SelectedPhotoState.SelectedPhotoSettings(
                    PhotoWithSettingsUI(
                        selectedPhoto = selectedPhoto,
                        selectedPhotoSize = PhotoSize.Small,
                        selectedFrameType = FrameType.Wood,
                        selectedFrameWidth = FrameWidth.Small
                    )
                )
            )


        }
    }


}

sealed interface PhotoSettingsState {
    data class PhotoSettings(
        val photoSizes: List<PhotoSize>,
        val frameTypes: List<FrameType>,
        val frameWidths: List<FrameWidth>
    ) : PhotoSettingsState
}

sealed interface SelectedPhotoState {
    data object Empty : SelectedPhotoState
    data class Error(@StringRes val errorMessage: Int) : SelectedPhotoState
    data class SelectedPhotoSettings(
        val selectedPhotoWithSettingsState: PhotoWithSettingsUI
    ) : SelectedPhotoState
}

enum class PhotoSize(
    val price: Double,
    @StringRes val nameResource: Int
) {
    Small(30.0, R.string.small),
    Medium(50.0, R.string.medium),
    Large(70.0, R.string.large)
}

enum class FrameType(
    val price: Double,
    @StringRes val nameResource: Int
) {
    Wood(10.0, R.string.wood),
    Plastic(25.0, R.string.plastic),
    Metal(50.0, R.string.metal)
}

enum class FrameWidth(
    val price: Double,
    @StringRes val nameResource: Int,
    val width: Int
) {
    Small(10.0, R.string.small_10mm, 10),
    Medium(25.0, R.string.medium_15mm, 15),
    Large(40.0, R.string.large_20mm, 20)
}