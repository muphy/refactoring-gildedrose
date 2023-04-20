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
        val name = name
        update1()
        age()
        update3()
    }

    protected open fun update3() {

    }

    protected open fun age() {
        sellIn -= 1
    }

    protected open fun update1() {
        if (quality > 0) {
            quality -= 1
        }
    }
}

class Brie(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0
) : BaseItem(name, sellIn, quality) {
    override fun update1() {
        if (quality < 50) {
            quality = quality + 1
        }
    }

    override fun update3() {
        if (sellIn < 0) {
            if (quality < 50) {
                quality += 1
            }
        }
    }
}

class Pass(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0
) : BaseItem(name, sellIn, quality) {
    override fun update1() {
        if (quality < 50) {
            quality = quality + 1
            if (sellIn < 11) {
                if (quality < 50) {
                    quality += 1
                }
            }
            if (sellIn < 6) {
                if (quality < 50) {
                    quality += 1
                }
            }
        }
    }

    override fun update3() {
        if (sellIn < 0) {
            quality = 0
        }
    }

}

class Elixir(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0
) : BaseItem(name, sellIn, quality) {
    override fun update1() {
        if (quality > 0) {
            quality -= 1
        }
    }

    override fun update3() {
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

    override fun update1() {
    }
    override fun age() {
    }

    override fun update3() {
    }

}

class Conjured(
    name: String,
    sellIn: Int = 0,
    quality: Int = 0
) : BaseItem(name, sellIn, quality) {
    override fun update1() {
        if (quality > 0) {
            quality -= 1
        }
    }

    override fun update3() {
        if (sellIn < 0) {
            if (quality > 0) {
                quality = quality - 1
            }
        }
    }

}
