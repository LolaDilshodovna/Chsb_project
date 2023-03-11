package com.jeff_skillrill.shopping_android_application.model

data class Book(
    var isFavourite: Boolean,
    var img: Int,
    var name: String,
    var price: Double,
    var isAddedToCart: Boolean,
    var count: Int = 1,
    var subTotal: Double = price,
) : java.io.Serializable