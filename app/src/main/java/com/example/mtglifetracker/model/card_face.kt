package com.example.mtglifetracker.model

data class card_face(
    val artist: String,
    val artist_id: String,
    val colors: List<String>,
    val illustration_id: String,
    val image_uris: ImageUrisX,
    val mana_cost: String,
    val name: String,
    val `object`: String,
    val oracle_text: String,
    val power: String,
    val toughness: String,
    val type_line: String
)