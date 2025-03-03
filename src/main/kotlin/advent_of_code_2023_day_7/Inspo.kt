package advent_of_code_2023_day_7
// https://raw.githubusercontent.com/eagely/adventofcode/refs/heads/main/src/main/kotlin/solutions/y2023/Day7.kt

//package solutions.y2023

//import Solution
//import utils.Utils.distinct
//import utils.Utils.rl
import java.io.File

class Day7 {

//    use Class Triple which is a Kotlin standard data class, hand, bid, value
//    create function to establish the value of a hand
//    function checks for multiple ocurrence of any character, and binds their value to an int of 6 available choices.
//    sorts according to score, uses compareHands method when multiciplies are equal - which check cards values..
//    use sorted (map) list to multiply by index

    fun solvePart1(input: File): Any {
        return input.readLines().asSequence().map { it.split(" ") }.map { Triple(it[0], it[1], valueOf(it[0])) }.sortedWith { left, right ->
            if (left.third != right.third) left.third.compareTo(right.third) // sorterar alla händer per rank om de inte har samma rank
            else compareHands(left.first, right.first, "23456789TJQKA") // bestämmer poäng för de händer som har samma rank
        }.map { it.first to it.second.toInt() }.mapIndexed { i, it -> (i + 1) * it.second }.sum()  // mappar alla händer till tuplar(hand, multiplikator), sedan mappar tuplarna med sitt sorterade Index
        // OCH multiplicerar utifrån index + 1 vilket resulterar i Int,  sedan summerar alla Int i sekvensen
    }

//    Sequence utför operationer när de behövs stegvis, vilket kan spara prestanda jämfört med samma operationer på en Collection.
//    På en Collection utförs alla operationer i steg, medans i en sequence utför alla operationer stegvis per element, och om något t.ex.
//    filtreras ut utförs inte följande operationer på dem.

    private fun valueOf(hand: String): Int {
//        val uniques = hand.replace("23456789TQKA", "").distinct().count() // why delete "23456789..." ??
        val uniques = hand.replace("23456789TQKA", "").toSet().joinToString("").count() // only deletes that exact string
        val counts = hand.groupingBy { it }.eachCount()
        val score = when {
            counts.any { it.value == 5 } -> 6
            counts.any { it.value == 4 } -> 5
            uniques == 2 && counts.any { it.value == 3 } -> 4
            counts.any { it.value == 3 } -> 3
            uniques == 3 && counts.any { it.value == 2 } -> 2
            uniques == 4 -> 1
            else -> 0
        }
        return score
    }

    private fun compareHands(hand1: String, hand2: String, order: String) = hand1.zip(hand2).find { it.first != it.second }?.let { if (order.indexOf(it.first) < order.indexOf(it.second)) -1 else 1 } ?: 0

    fun solvePart2(input: File): Any {
        return input.readLines().asSequence().map { it.split(" ") }.map { Triple(it[0], it[1], valueOf(it[0].map { if (it == 'J') "23456789TQKA" else it.toString() }.joinToString(""))) }.sortedWith { left, right ->
            if (left.third != right.third) left.third.compareTo(right.third)
            else compareHands(left.first, right.first, "J23456789TQKA")
        }.map { it.first to it.second.toInt() }.mapIndexed { i, it -> (i + 1) * it.second }.sum()
    }
}