package com.example.museumapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tours"
)
data class Tour(
    val average_rating: String,
    val duration: String,
    val header_image: String,
    val permalink: String,
    val rating_count: Int,
    val retail_price: String,
    val sales_price: String,
    val sku: String,
    val thumbnail: String,
    val title: String,
    @PrimaryKey val id: Int? = null
)



