package com.example.oblig3_verkhalantseva.extensions

import android.content.res.Resources

fun Int.fromDp() =
    (this / Resources.getSystem().displayMetrics.density)