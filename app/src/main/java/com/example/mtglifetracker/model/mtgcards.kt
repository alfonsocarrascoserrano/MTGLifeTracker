package com.example.mtglifetracker.model

data class mtgcards(
    val `data`: List<MTGCard>,
    val has_more: Boolean,
    val `object`: String,
    val total_cards: Int
)