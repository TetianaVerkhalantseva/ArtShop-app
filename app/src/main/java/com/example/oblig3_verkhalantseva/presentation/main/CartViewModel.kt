package com.example.oblig3_verkhalantseva.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_verkhalantseva.presentation.main.data.CartPhoto
import com.example.oblig3_verkhalantseva.presentation.main.mapper.countTotalPrice
import com.example.oblig3_verkhalantseva.presentation.main.mapper.toCartPhoto
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.data.PhotoWithSettingsUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val _cart: MutableStateFlow<CartState> = MutableStateFlow(CartState.Empty)
    val cart = _cart.asStateFlow()

    private val _addToCartProcessState: MutableStateFlow<AddToCartProcessState> =
        MutableStateFlow(AddToCartProcessState.Nothing)
    val addToCartProcessState = _addToCartProcessState.asStateFlow()

    fun addToCart(photo: PhotoWithSettingsUI) {
        viewModelScope.launch {
            _addToCartProcessState.emit(AddToCartProcessState.InProgress)
            val value = _cart.value
            when (value) {
                CartState.Empty -> {
                    _cart.emit(
                        CartState.Cart(
                            amount = 1,
                            photos = listOf(
                                photo.toCartPhoto()
                            ),
                            totalPrice = photo.totalPrice
                        )
                    )
                }

                is CartState.Cart -> {
                    val photos = value.photos.toMutableList().apply { add(photo.toCartPhoto()) }
                    _cart.emit(
                        CartState.Cart(
                            amount = photos.size,
                            photos = photos,
                            photos.countTotalPrice()
                        )
                    )
                }
            }
            _addToCartProcessState.emit(AddToCartProcessState.Finished)
        }
    }

    fun clearAddToCartProcessState() {
        CoroutineScope(Dispatchers.IO).launch {
            _addToCartProcessState.emit(AddToCartProcessState.Nothing)
        }
    }

    fun removeCartPhoto(photo: CartPhoto) {
        viewModelScope.launch {
            val value = _cart.value
            if (value is CartState.Cart) {
                val resultPhotos = value.photos.toMutableList().apply { remove(photo) }
                _cart.emit(
                    if (resultPhotos.isNotEmpty()) {
                        CartState.Cart(
                            amount = resultPhotos.size,
                            photos = resultPhotos,
                            totalPrice = resultPhotos.countTotalPrice()
                        )
                    } else CartState.Empty
                )
            }
        }
    }

    fun buyAllPhotos() {
        viewModelScope.launch {
            _cart.emit(CartState.Empty)
        }
    }

}

sealed interface CartState {
    data object Empty : CartState
    data class Cart(
        val amount: Int,
        val photos: List<CartPhoto>,
        val totalPrice: Double
    ) : CartState

}

sealed interface AddToCartProcessState {
    data object Nothing : AddToCartProcessState
    data object InProgress : AddToCartProcessState
    data object Error : AddToCartProcessState
    data object Finished : AddToCartProcessState
}