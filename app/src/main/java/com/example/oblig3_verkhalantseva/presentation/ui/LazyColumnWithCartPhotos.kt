package com.example.oblig3_verkhalantseva.presentation.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.oblig3_verkhalantseva.extensions.fromDp
import com.example.oblig3_verkhalantseva.presentation.main.data.CartPhoto

@Composable
fun LazyColumnWithCartPhotos(
    photos: List<CartPhoto>,
    onItemRemove: (CartPhoto) -> Unit,
    modifier: Modifier = Modifier,
    itemModifier: Modifier = Modifier,
    itemsPadding: Dp
) {
    val itemsHeight = remember { mutableIntStateOf(0) }
    LazyColumn(
        modifier = modifier
            .height((itemsHeight.intValue.fromDp().dp + itemsPadding * 2) * 2)
    ) {
        items(photos) { photo ->
            LazyCartInfoItem(
                modifier = itemModifier
                    .onSizeChanged {
                        if (itemsHeight.intValue != it.height)
                            itemsHeight.intValue = it.height
                    },
                startPhoto = photo.photo.imageResId,
                title = stringResource(id = photo.photo.titleResId),
                photoSize = stringResource(id = photo.photoSize.nameResource),
                frameType = stringResource(id = photo.frameType.nameResource),
                frameWidth = photo.frameWidth.width,
                totalPrice = photo.totalPrice,
                verticalPadding = itemsPadding,
                onClickRemoveClick = { onItemRemove(photo) }
            )
        }
    }
}