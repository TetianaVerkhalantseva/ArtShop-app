package com.example.oblig3_verkhalantseva.presentation.artists

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_verkhalantseva.data.PhotosDB
import com.example.oblig3_verkhalantseva.presentation.artists.data.ArtistWithPhotos
import com.example.oblig3_verkhalantseva.presentation.artists.data.ArtistsWithPhotosUI
import com.example.oblig3_verkhalantseva.presentation.artists.mapper.toUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArtistsViewModel(): ViewModel() {

    private val photosDB = PhotosDB()

    private val _artistsWithPhotos : MutableStateFlow<List<ArtistsWithPhotosUI>?> = MutableStateFlow(null)
    val artistsWithPhotos: StateFlow<List<ArtistsWithPhotosUI>?> = _artistsWithPhotos.asStateFlow()

    fun getArtistsWithPhotos() {
        viewModelScope.launch {
            val photos = photosDB.getAllPhotos()
            val artistsWithPhotos = mutableListOf<ArtistWithPhotos>()

            photos.forEach { photo ->
                val artistsIndex = artistsWithPhotos.indexOfFirst {
                    it.artist.id == photo.artist.id
                }

                if (artistsIndex == -1) {
                    artistsWithPhotos.add(
                        ArtistWithPhotos(
                        artist = photo.artist
                    ).apply {
                        addPhoto(photo.toUI())
                        }
                    )
                }
                else {
                    artistsWithPhotos[artistsIndex].addPhoto(photo.toUI())
                }

            }
            _artistsWithPhotos.emit(artistsWithPhotos.map { it.toUI() })
            Log.e("WatchingSomeStuff", "final artists with photos = $artistsWithPhotos")

        }
    }
}