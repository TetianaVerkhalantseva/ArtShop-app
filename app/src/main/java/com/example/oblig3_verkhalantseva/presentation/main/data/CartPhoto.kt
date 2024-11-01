package com.example.oblig3_verkhalantseva.presentation.main.data

import com.example.oblig3_verkhalantseva.data.model.Photo
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.FrameType
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.FrameWidth
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.PhotoSize

data class CartPhoto(
    val photo: Photo,
    val photoSize: PhotoSize,
    val frameType: FrameType,
    val frameWidth: FrameWidth,
    val totalPrice: Double
)