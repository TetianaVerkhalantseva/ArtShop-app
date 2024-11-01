package com.example.oblig3_verkhalantseva.data

import com.example.oblig3_verkhalantseva.R
import com.example.oblig3_verkhalantseva.data.model.Artist
import com.example.oblig3_verkhalantseva.data.model.Category
import com.example.oblig3_verkhalantseva.data.model.Photo

class PhotosDB() {


    fun getAllPhotos() =
        listOf<Photo>(
            Photo(
                id = 0,
                titleResId = R.string.highbuilding,
                imageResId = R.drawable.highbuilding,
                artist = Artist(
                    id = 0,
                    name = R.string.artist1,
                    imageResId = R.drawable.android_artist1,
                ),
                category = Category.ARCHITECTURE,
                price = 50.5F
            ),
            Photo(
                id = 1,
                titleResId = R.string.skyline,
                imageResId = R.drawable.skyline,
                artist = Artist(
                    id = 0,
                    name = R.string.artist1,
                    imageResId = R.drawable.android_artist1,
                ),
                category = Category.ARCHITECTURE,
                price = 73.5F
            ),
            Photo(
                id = 2,
                titleResId = R.string.skiing,
                imageResId = R.drawable.skiing,
                artist = Artist(
                    id = 0,
                    name = R.string.artist1,
                    imageResId = R.drawable.android_artist1,
                ),
                category = Category.HOBBY,
                price = 70.5F
            ),
            Photo(
                id = 3,
                titleResId = R.string.desert,
                imageResId = R.drawable.desert,
                artist = Artist(
                    id = 0,
                    name = R.string.artist1,
                    imageResId = R.drawable.android_artist1,
                ),
                category = Category.NATURE,
                price = 85.3F
            ),
            Photo(
                id = 4,
                titleResId = R.string.frankie,
                imageResId = R.drawable.frankie,
                artist = Artist(
                    id = 0,
                    name = R.string.artist1,
                    imageResId = R.drawable.android_artist1,
                ),
                category = Category.DOGS,
                price = 92.3F
            ),
            Photo(
                id = 5,
                titleResId = R.string.koda,
                imageResId = R.drawable.koda,
                artist = Artist(
                    id = 0,
                    name = R.string.artist1,
                    imageResId = R.drawable.android_artist1,
                ),
                category = Category.DOGS,
                price = 89.3F
            ),
            Photo(
                id = 6,
                titleResId = R.string.car,
                imageResId = R.drawable.car,
                artist = Artist(
                    id = 0,
                    name = R.string.artist1,
                    imageResId = R.drawable.android_artist1,
                ),
                category = Category.VARIOUS,
                price = 90.0F
            ),
            Photo(
                id = 7,
                titleResId = R.string.music,
                imageResId = R.drawable.music,
                artist = Artist(
                    id = 1,
                    name = R.string.artist2,
                    imageResId = R.drawable.android_artist2,
                ),
                category = Category.HOBBY,
                price = 65.5F
            ),
            Photo(
                id = 8,
                titleResId = R.string.walking,
                imageResId = R.drawable.walking,
                artist = Artist(
                    id = 1,
                    name = R.string.artist2,
                    imageResId = R.drawable.android_artist2,
                ),
                category = Category.HOBBY,
                price = 66.7F
            ),
            Photo(
                id = 9,
                titleResId = R.string.river,
                imageResId = R.drawable.river,
                artist = Artist(
                    id = 1,
                    name = R.string.artist2,
                    imageResId = R.drawable.android_artist2,
                ),
                category = Category.NATURE,
                price = 54.7F
            ),
            Photo(
                id = 10,
                titleResId = R.string.bridge,
                imageResId = R.drawable.bridge,
                artist = Artist(
                    id = 1,
                    name = R.string.artist2,
                    imageResId = R.drawable.android_artist2,
                ),
                category = Category.ARCHITECTURE,
                price = 94.5F
            ),
            Photo(
                id = 11,
                titleResId = R.string.leroy,
                imageResId = R.drawable.leroy,
                artist = Artist(
                    id = 1,
                    name = R.string.artist2,
                    imageResId = R.drawable.android_artist2,
                ),
                category = Category.DOGS,
                price = 97.5F
            ),
            Photo(
                id = 12,
                titleResId = R.string.photographing,
                imageResId = R.drawable.photographing,
                artist = Artist(
                    id = 2,
                    name = R.string.artist3,
                    imageResId = R.drawable.android_artist3,
                ),
                category = Category.HOBBY,
                price = 77.1F
            ),
            Photo(
                id = 13,
                titleResId = R.string.filming,
                imageResId = R.drawable.filming,
                artist = Artist(
                    id = 2,
                    name = R.string.artist3,
                    imageResId = R.drawable.android_artist3,
                ),
                category = Category.HOBBY,
                price = 63.3F
            ),
            Photo(
                id = 14,
                titleResId = R.string.canyon,
                imageResId = R.drawable.canyon,
                artist = Artist(
                    id = 2,
                    name = R.string.artist3,
                    imageResId = R.drawable.android_artist3,
                ),
                category = Category.NATURE,
                price = 99.5F
            ),
            Photo(
                id = 15,
                titleResId = R.string.cityscape,
                imageResId = R.drawable.cityscape,
                artist = Artist(
                    id = 2,
                    name = R.string.artist3,
                    imageResId = R.drawable.android_artist3,
                ),
                category = Category.ARCHITECTURE,
                price = 64.0F
            ),
            Photo(
                id = 16,
                titleResId = R.string.nox,
                imageResId = R.drawable.nox,
                artist = Artist(
                    id = 2,
                    name = R.string.artist3,
                    imageResId = R.drawable.android_artist3,
                ),
                category = Category.DOGS,
                price = 84.6F
            ),
            Photo(
                id = 17,
                titleResId = R.string.umbrellas,
                imageResId = R.drawable.umbrellas,
                artist = Artist(
                    id = 2,
                    name = R.string.artist3,
                    imageResId = R.drawable.android_artist3,
                ),
                category = Category.VARIOUS,
                price = 55.6F
            ),
            Photo(
                id = 18,
                titleResId = R.string.chess,
                imageResId = R.drawable.chess,
                artist = Artist(
                    id = 3,
                    name = R.string.artist4,
                    imageResId = R.drawable.android_artist4,
                ),
                category = Category.HOBBY,
                price = 88.2F
            ),
            Photo(
                id = 19,
                titleResId = R.string.sandstone,
                imageResId = R.drawable.sandstone,
                artist = Artist(
                    id = 3,
                    name = R.string.artist4,
                    imageResId = R.drawable.android_artist4,
                ),
                category = Category.NATURE,
                price = 55.6F
            ),
            Photo(
                id = 20,
                titleResId = R.string.skyscrapers,
                imageResId = R.drawable.skyscrapers,
                artist = Artist(
                    id = 3,
                    name = R.string.artist4,
                    imageResId = R.drawable.android_artist4,
                ),
                category = Category.ARCHITECTURE,
                price = 44.1F
            ),
            Photo(
                id = 21,
                titleResId = R.string.bella,
                imageResId = R.drawable.bella,
                artist = Artist(
                    id = 3,
                    name = R.string.artist4,
                    imageResId = R.drawable.android_artist4,
                ),
                category = Category.DOGS,
                price = 77.8F
            ),
            Photo(
                id = 22,
                titleResId = R.string.tzeitel,
                imageResId = R.drawable.tzeitel,
                artist = Artist(
                    id = 3,
                    name = R.string.artist4,
                    imageResId = R.drawable.android_artist4,
                ),
                category = Category.DOGS,
                price = 88.2F
            ),
            Photo(
                id = 23,
                titleResId = R.string.citylights,
                imageResId = R.drawable.citylights,
                artist = Artist(
                    id = 3,
                    name = R.string.artist4,
                    imageResId = R.drawable.android_artist4,
                ),
                category = Category.VARIOUS,
                price = 34.2F
            ),
            Photo(
                id = 24,
                titleResId = R.string.wall,
                imageResId = R.drawable.wall,
                artist = Artist(
                    id = 4,
                    name = R.string.artist5,
                    imageResId = R.drawable.android_artist5,
                ),
                category = Category.ARCHITECTURE,
                price = 92.5F
            ),
            Photo(
                id = 25,
                titleResId = R.string.moana,
                imageResId = R.drawable.moana,
                artist = Artist(
                    id = 4,
                    name = R.string.artist5,
                    imageResId = R.drawable.android_artist5,
                ),
                category = Category.DOGS,
                price = 88.5F
            ),
            Photo(
                id = 26,
                titleResId = R.string.parking,
                imageResId = R.drawable.parking,
                artist = Artist(
                    id = 4,
                    name = R.string.artist5,
                    imageResId = R.drawable.android_artist5,
                ),
                category = Category.VARIOUS,
                price = 43.7F
            ),
            Photo(
                id = 27,
                titleResId = R.string.parrot,
                imageResId = R.drawable.parrot,
                artist = Artist(
                    id = 4,
                    name = R.string.artist5,
                    imageResId = R.drawable.android_artist5,
                ),
                category = Category.VARIOUS,
                price = 59.3F
            ),
            Photo(
                id = 28,
                titleResId = R.string.plasmaball,
                imageResId = R.drawable.plasmaball,
                artist = Artist(
                    id = 4,
                    name = R.string.artist5,
                    imageResId = R.drawable.android_artist5,
                ),
                category = Category.VARIOUS,
                price = 88.6F
            ),
            Photo(
                id = 29,
                titleResId = R.string.faye,
                imageResId = R.drawable.faye,
                artist = Artist(
                    id = 5,
                    name = R.string.artist6,
                    imageResId = R.drawable.android_artist6,
                ),
                category = Category.DOGS,
                price = 73.9F
            ),
            Photo(
                id = 30,
                titleResId = R.string.lola,
                imageResId = R.drawable.lola,
                artist = Artist(
                    id = 5,
                    name = R.string.artist6,
                    imageResId = R.drawable.android_artist6,
                ),
                category = Category.DOGS,
                price = 90.5F
            ),
            Photo(
                id = 31,
                titleResId = R.string.seahorses,
                imageResId = R.drawable.seahorses,
                artist = Artist(
                    id = 5,
                    name = R.string.artist6,
                    imageResId = R.drawable.android_artist6,
                ),
                category = Category.VARIOUS,
                price = 77.2F
            ),
            Photo(
                id = 32,
                titleResId = R.string.grass,
                imageResId = R.drawable.grass,
                artist = Artist(
                    id = 5,
                    name = R.string.artist6,
                    imageResId = R.drawable.android_artist6,
                ),
                category = Category.VARIOUS,
                price = 55.0F
            ),
        )

}