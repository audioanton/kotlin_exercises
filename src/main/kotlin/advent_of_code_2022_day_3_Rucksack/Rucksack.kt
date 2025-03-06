package advent_of_code_2022_day_3_Rucksack

import java.io.File

fun main () {
    val lines = File("src/main/kotlin/advent_of_code_2022_day_3_Rucksack/input.txt").readLines()

    val letters = ('A'..'z').toList().filter { it.isLetter() }.partition { it.isUpperCase() }
    val letterScores = letters.first.mapIndexed { idx, c -> c to idx+27 }.toMap() + letters.second.mapIndexed { idx, c -> c to idx+1 }.toMap()

    var sumPartOne = 0

    for (line in lines) {
        val compartmentOne = line.substring(0, line.length / 2).toSet().toString()
        val compartmentTwo = line.substring(line.length / 2).toSet().joinToString("")
        sumPartOne += getPriorityScore(listOf(compartmentOne, compartmentTwo), letterScores) { l, c  -> (c in l[1]) }
    }
    println(sumPartOne)

    var sumPartTwo = 0

    for (i in lines.indices step 3) {
        val rucksacks = listOf(lines[i], lines[i+1], lines[i+2]).map { it.toSet().joinToString("") }
        sumPartTwo += getPriorityScore(rucksacks, letterScores) { l, c -> (c in l[1] && c in l[2]) }
    }
    println(sumPartTwo)
}

fun getPriorityScore(compartments : List<String>, scores : Map<Char, Int>, predicate : (List<String>, Char) -> Boolean ) : Int {
    return compartments[0].filter { predicate(compartments, it) }.map { scores[it] ?: 0 }.sum()
}



