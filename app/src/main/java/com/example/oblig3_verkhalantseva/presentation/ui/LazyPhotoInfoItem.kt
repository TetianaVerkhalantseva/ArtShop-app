package com.example.oblig3_verkhalantseva.presentation.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.oblig3_verkhalantseva.R

@Composable
fun LazyPhotoInfoItem(
    modifier : Modifier = Modifier,
    @DrawableRes startPhoto: Int,
    title: String,
    amountOfPhotos: Int,
    mostExpensivePhotoName: String,
    mostExpensivePhotoPrice: Float,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = modifier,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .sizeIn(minHeight = 72.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(8.dp))

                ) {
                    Image(
                        painter = painterResource(startPhoto),
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.FillWidth
                    )
                }
                Spacer(Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = title,
                        style = MaterialTheme.typography.displaySmall)
                    Text(text = stringResource(R.string.number_of_photos, amountOfPhotos),
                        style = MaterialTheme.typography.bodySmall)
                    Text(text = stringResource(R.string.most_expensive_photo, mostExpensivePhotoName),
                        style = MaterialTheme.typography.bodySmall)
                    Text(text = stringResource(R.string.most_expensive_photo_price, mostExpensivePhotoPrice),
                        style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}