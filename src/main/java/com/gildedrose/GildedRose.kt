package com.gildedrose

class GildedRose(val items: List<Item>) {
    fun updated() : GildedRose {
        return GildedRose(items.map { it.updated() })
    }

}
