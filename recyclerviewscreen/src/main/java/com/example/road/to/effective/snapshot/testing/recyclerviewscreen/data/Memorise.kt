package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.English

data class Memorise(
    val id: Int,
    val srcLang: Language = English,
    val destLang: Language = English,
    val translations: List<Translation> = emptyList(),
    val title: String = "",
    val text: String = "",
    val landmark: Int = 0
)
