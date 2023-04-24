package com.gildedrose

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

operator fun ((Int, Int) -> Int).times(multiplier: Int) = { P1: Int, P2: Int ->
    this(P1,P2) * multiplier
}
