package advent_of_code_2023_day_7_CamelCards

import java.io.File


// algorithm:
//    create data class hand, data class helps with comparing
//    for each line, save hand 'multiplicities' ordered by highest ocurrence, convert all card values to int and save their values ordered, save bid value
//    sort all hands according to their 'rank' - which is a combination of their multiplicities and card values, lowest rank first
//    multiply all hands bid value by their rank value
//    sum all the results

data class ParsedHand(val hand: List<Int>, val multiples: List<Int>, val bid: Int) : Comparable<ParsedHand> {
    override fun compareTo(other: ParsedHand): Int = compareValuesBy(this, other,
        { it.multiples[0] },
        { it.multiples[1] },
        { it.hand.zip(other.hand).map { (a, b) -> a - b }.firstOrNull { i -> i != 0 } ?: 0 }
    )
}

fun parseHand(cards : String, bid : String) : ParsedHand {
    val camelValues : Map<Char, Int> = "TJQKA".map { it }.withIndex().associate { it.value to it.index + 10 }
    val parsedHand = cards.map { camelValues[it] ?: it.digitToInt() }
    val multiples = cards.groupingBy { it }.eachCount().values.sortedDescending()
    return ParsedHand(parsedHand, multiples, bid.toInt())
}

fun main() {
    val lines : List<String> = File("src/main/kotlin/advent_of_code_2023_day_7_CamelCards/input.txt").readLines()

    fun getAnswer(lines : List<String>) : Int {
        return lines.asSequence().map { it.split(" ") } // using lambda with variables inside map to create new Object
        .map { (cards, bid) -> parseHand(cards, bid) }
        .sorted()
        .mapIndexed { index, parsedHand -> parsedHand.bid * (index +1) }
        .sum()
   }

    println(getAnswer(lines))
}