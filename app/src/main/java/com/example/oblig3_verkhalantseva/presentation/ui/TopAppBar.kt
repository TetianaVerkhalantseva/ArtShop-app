package com.example.oblig3_verkhalantseva.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.oblig3_verkhalantseva.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector? = ImageVector.vectorResource(id = R.drawable.back),
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    titleColor: Color = MaterialTheme.colorScheme.primary,
    onNavigationIconClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .border(1.dp, Color.Black)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.displayLarge
                    )
                }
            },
            navigationIcon = {
                navigationIcon?.let {
                    Image(
                        modifier = Modifier
                            .clickable {
                                onNavigationIconClick.invoke()
                            }
                            .padding(16.dp),
                        imageVector = it,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = backgroundColor,
                titleContentColor = titleColor
            ),
        )
    }
}