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

open class BaseItem(
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

class Sulfuras(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0,
    aging: () -> Int = { 0 },
    override val degradation: (Int, Int) -> Int = { _: Int, _: Int -> 0 },
    override val saturation: (Int) -> Int = { quality: Int -> quality }
) : BaseItem(name, sellIn, quality) {

}

class Brie(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0,
    override val degradation: (Int, Int) -> Int = { sellIn: Int, _: Int ->
        when {
            sellIn < 0 -> -2
            else -> -1
        }
    }
) : BaseItem(name, sellIn, quality) {

}

class Pass(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0,
    override val degradation: (Int, Int) -> Int = { sellIn: Int, quality: Int ->
        when {
            sellIn < 0 -> quality
            sellIn < 5 -> -3
            sellIn < 10 -> -2
            else -> -1
        }
    }
) : BaseItem(name, sellIn, quality) {

}

class Elixir(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0
) : BaseItem(name, sellIn, quality) {}

class Conjured(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0,
    override val degradation: (Int, Int) -> Int = { sellIn: Int, _: Int ->
        when {
            sellIn < 0 -> 2
            else -> 1
        }
    }
) : BaseItem(name, sellIn, quality)

