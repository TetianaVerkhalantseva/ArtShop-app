package com.example.oblig3_verkhalantseva.presentation.selectedPhoto.data

import com.example.oblig3_verkhalantseva.data.model.Photo
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.FrameType
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.FrameWidth
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.PhotoSize

data class PhotoWithSettingsUI(
    val selectedPhoto: Photo,
    val selectedPhotoSize: PhotoSize,
    val selectedFrameType: FrameType,
    val selectedFrameWidth: FrameWidth
) {

    val totalPrice: Double
        get() = selectedPhoto.price + selectedPhotoSize.price + selectedFrameType.price + selectedFrameWidth.price

}