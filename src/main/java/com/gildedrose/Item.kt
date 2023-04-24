package com.gildedrose

open class Item(
    val name: String,
    var sellIn: Int = 0,
    var quality: Int = 0
) {
    override fun toString() = "$name, $sellIn, $quality"
}
// in order to add operations to a type without changing initial type we could subclass type
// so we could say we've got a class which is a base item

object Aging {
    val standard: () -> Int = { 1 }
    val none: () -> Int = { 0 }
}

object Degradation {
    val standard: (Int, Int) -> Int = { sellIn: Int, _: Int ->
        when {
            sellIn < 0 -> 2
            else -> 1
        }
    }
    val none: (Int, Int) -> Int = { _, _ -> 0 }
}

object Saturation {
    val standard: (Int) -> Int = { quality: Int ->
        when {
            quality < 0 -> 0
            quality > 50 -> 50
            else -> quality
        }
    }
    val none: (Int) -> Int = { quality: Int -> quality }
}

class BaseItem(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0,
    private val aging: () -> Int = Aging.standard,
    private val degradation: (Int, Int) -> Int = Degradation.standard,
    private val saturation: (Int) -> Int = Saturation.standard
) : Item(name, sellIn, quality) {
    fun update() {
        sellIn = sellIn - aging()
        quality = saturation(quality - degradation(sellIn, quality))
    }

}

fun Sulfuras(name: String, sellIn: Int = 0, quality: Int = 0) = BaseItem(
    name,
    sellIn,
    quality,
    aging = Aging.none,
    degradation = Degradation.none,
    saturation = Saturation.none
)

fun Brie(name: String, sellIn: Int = 0, quality: Int = 0) = BaseItem(
    name,
    sellIn,
    quality,
    degradation = { currentSellIn: Int, _: Int ->
        when {
            currentSellIn < 0 -> -2
            else -> -1
        }
    }
)

fun Pass(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0,
) = BaseItem(name,
    sellIn,
    quality,
    degradation = { currentSellIn: Int, currentQuality: Int ->
        when {
            currentSellIn < 0 -> currentQuality
            currentSellIn < 5 -> -3
            currentSellIn < 10 -> -2
            else -> -1
        }
    })

fun Elixir(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0,
) = BaseItem(name, sellIn, quality)

fun Conjured(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0,
) = BaseItem(name,
    sellIn,
    quality,
    degradation = { currentSellIn: Int, _: Int ->
        when {
            currentSellIn < 0 -> 4
            else -> 2
        }
    })

