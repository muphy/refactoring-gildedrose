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
    quality: Int = 0
) : Item(name, sellIn, quality) {
    fun update() {
        age()
        degrade()
        saturate()
    }

    protected open fun age() {
        sellIn -= 1
    }

    protected open fun degrade() {
        if (quality > 0) {
            quality -= 1
        }
    }

    protected open fun saturate() {
        if(quality < 0 ) quality = 0
        if(quality > 50) quality = 50
    }
}

class Brie(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0
) : BaseItem(name, sellIn, quality) {
    override fun degrade() {
        if (sellIn < 0) {
            quality += 2
        } else {
            quality += 1
        }
    }

}

class Pass(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0
) : BaseItem(name, sellIn, quality) {
    override fun degrade() = when {
        sellIn < 0 -> quality = 0
        sellIn < 5 -> quality += 3
        sellIn < 10 -> quality += 2
        else -> quality += 1
    }

}

class Elixir(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0
) : BaseItem(name, sellIn, quality) {
    override fun degrade() {
        if (quality > 0) {
            quality -= 1
        }
        if (sellIn < 0) {
            if (quality > 0) {
                quality -= 1
            }
        }
    }


}

class Sulfuras(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0
) : BaseItem(name, sellIn, quality) {

    override fun degrade() {
    }
    override fun age() {
    }
    override fun saturate() {
        if(quality < 0 ) quality = 0
        if(quality > 50) quality = 80
    }

}

class Conjured(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0
) : BaseItem(name, sellIn, quality) {
    override fun degrade() {
        if (quality > 0) {
            quality -= 1
        }
        if (sellIn < 0) {
            if (quality > 0) {
                quality = quality - 1
            }
        }
    }

}
