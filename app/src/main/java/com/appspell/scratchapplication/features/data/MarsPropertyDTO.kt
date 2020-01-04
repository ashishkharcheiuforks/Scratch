package com.appspell.scratchapplication.features.data

import com.squareup.moshi.Json

data class MarsPropertyDTO(
    val id: String,
    @Json(name = "img_src")
    val imgSrc: String,
    val type: String,
    val price: Double
)