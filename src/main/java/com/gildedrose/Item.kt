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
    quality: Int = 0
) : Item(name, sellIn, quality) {
    fun update() {
        val name = name
        if (name != "Aged Brie"
            && name != "Backstage passes to a TAFKAL80ETC concert"
        ) {
            if (quality > 0) {
                if (name != "Sulfuras, Hand of Ragnaros") {
                    quality = quality - 1
                }
            }
        } else {
            if (quality < 50) {
                quality = quality + 1
                if (name == "Backstage passes to a TAFKAL80ETC concert") {
                    if (sellIn < 11) {
                        if (quality < 50) {
                            quality = quality + 1
                        }
                    }
                    if (sellIn < 6) {
                        if (quality < 50) {
                            quality = quality + 1
                        }
                    }
                }
            }
        }
        if (name != "Sulfuras, Hand of Ragnaros") {
            sellIn = sellIn - 1
        }
        if (sellIn < 0) {
            if (name != "Aged Brie") {
                if (name != "Backstage passes to a TAFKAL80ETC concert") {
                    if (quality > 0) {
                        if (name != "Sulfuras, Hand of Ragnaros") {
                            quality = quality - 1
                        }
                    }
                } else {
                    quality = 0
                }
            } else {
                if (quality < 50) {
                    quality = quality + 1
                }
            }
        }
    }
}

