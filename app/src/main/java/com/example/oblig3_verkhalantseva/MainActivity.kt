package com.example.oblig3_verkhalantseva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.oblig3_verkhalantseva.navigation.PhotosNavHost
import com.example.oblig3_verkhalantseva.ui.theme.Oblig3_VerkhalantsevaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Oblig3_VerkhalantsevaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PhotosNavHost()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Oblig3_VerkhalantsevaTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            PhotosNavHost()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainDarkPreview() {
    Oblig3_VerkhalantsevaTheme(
        darkTheme = true
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            PhotosNavHost()
        }
    }
}