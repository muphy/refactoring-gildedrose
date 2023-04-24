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

class BaseItem(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0,
    private val aging: () -> Int = { 1 },
    protected open val degradation: (Int, Int) -> Int = { sellIn: Int, quality: Int ->
        when {
            sellIn < 0 -> 2
            else -> 1
        }
    }, protected open val saturation: (Int) -> Int = { quality: Int ->
        when {
            quality < 0 -> 0
            quality > 50 -> 50
            else -> quality
        }
    }
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
    aging = { 0 },
    degradation = { _, _ -> 0 },
    saturation = { quality: Int -> quality }
)

fun Brie(name: String, sellIn: Int = 0, quality: Int = 0) = BaseItem(
    name,
    sellIn,
    quality,
    degradation = { sellIn: Int, _: Int ->
        when {
            sellIn < 0 -> -2
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
    degradation = { sellIn: Int, quality: Int ->
        when {
            sellIn < 0 -> quality
            sellIn < 5 -> -3
            sellIn < 10 -> -2
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
    degradation = { sellIn: Int, _: Int ->
        when {
            sellIn < 0 -> 2
            else -> 1
        }
    })

