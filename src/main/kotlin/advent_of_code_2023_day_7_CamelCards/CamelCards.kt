package advent_of_code_2023_day_7_CamelCards

import java.io.File

// https://adventofcode.com/2023/day/7

// algorithm:
//    create data class hand, data class helps with comparing
//    for each line, save hand 'multiplicities' ordered by highest ocurrence, convert all card values to int and save their values ordered, save bid value
//    sort all hands according to their 'rank' - which is a combination of their multiplicities and card values, lowest rank first
//    multiply all hands bid value by their rank value
//    sum all the results

data class CamelHand(val hand: List<Int>, val combinations: List<Int>, val bid: Int) : Comparable<CamelHand> {
    override fun compareTo(other: CamelHand): Int = compareValuesBy(this, other,
        { it.combinations[0] }, // vararg, can take in variable number of parameters
        { it.combinations[1] },
        { it.hand.zip(other.hand).map { (a, b) -> a - b }.firstOrNull { i -> i != 0 } ?: 0 }
    )
}

fun parseHand(cards : String, bid : String, camelValues : Map<Char, Int>) : CamelHand {
    val numberHand = cards.map { camelValues[it] ?: it.digitToInt() }
    val combinations = cards.groupingBy { it }.eachCount().values.sortedDescending() // grouping first, then mapping their value to a count of their occurences
    return CamelHand(numberHand, combinations, bid.toInt())
}

fun main() {
    val lines : List<String> = File("src/main/kotlin/advent_of_code_2023_day_7_CamelCards/input.txt").readLines()
    val camelValues = "TJQKA".map { it }.withIndex().associate { it.value to it.index + 10 }

    fun getAnswer(lines : List<String>) : Int { // asSequence() executes pipeline "lazy" and improves performance - stepwise per element instead of "eagerly" for entire collection, no intermediary lists
        return lines.asSequence().map { it.split(" ") }
        .map { (cards, bid) -> parseHand(cards, bid, camelValues) }
        .sorted() // requires full list
        .mapIndexed { index, parsedHand -> parsedHand.bid * (index +1) }
        .sum()
   }

    println(getAnswer(lines))
}