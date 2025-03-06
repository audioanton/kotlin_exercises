package advent_of_code_2022_day_3_Rucksack

import java.io.File

fun main () {
    val lines = File("src/main/kotlin/advent_of_code_2022_day_3_Rucksack/input.txt").readLines()

    val letters = ('A'..'z').toList().filter { it.isLetter() }.partition { it.isUpperCase() }
    val letterScores = letters.first.mapIndexed { idx, c -> c to idx+27 }.toMap() + letters.second.mapIndexed { idx, c -> c to idx+1 }.toMap()

    val sumPartOne = lines.sumOf { getCompartments(it).getPriorityScore(letterScores) { l, c -> c in l[1] } }

    val sumPartTwoFunctional = (lines.indices step 3).sumOf { i -> listOf(lines[i], lines[i + 1], lines[i + 2]).map { it.toSet().joinToString("") }
        .getPriorityScore(letterScores) { l, c -> (c in l[1] && c in l[2]) }
    }

    // imperative, mutable..
    var sumPartTwo = 0
    for (i in lines.indices step 3) {
        val rucksacks = listOf(lines[i], lines[i+1], lines[i+2]).map { it.toSet().joinToString("") }
        sumPartTwo += rucksacks.getPriorityScore(letterScores) { l, c -> (c in l[1] && c in l[2]) }
    }

    println(sumPartOne)
    println(sumPartTwo)
    println(sumPartTwoFunctional)
}

fun getCompartments(line : String) : List<String> = listOf(line.substring(0, line.length / 2).toSet().joinToString(""), line.substring(line.length / 2).toSet().joinToString(""))

// extension function
fun List<String>.getPriorityScore( scores : Map<Char, Int>, predicate : (List<String>, Char) -> Boolean ) : Int {
    return this[0].filter { predicate(this, it) }.map { scores[it] ?: 0 }.sum()
}



