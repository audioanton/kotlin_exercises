package advent_of_code_2022_day_3_Rucksack

import java.io.File

// part two
// separate each three lines into separate lists
// do the same as part one but for these groups


fun main () {
    val lines = File("src/main/kotlin/advent_of_code_2022_day_3_Rucksack/input.txt").readLines()

    val letters = ('A'..'z').toList().filter { it.isLetter() }.partition { it.isUpperCase() }
    val letterScores = letters.first.mapIndexed { idx, c -> c to idx+27 }.toMap() + letters.second.mapIndexed { idx, c -> c to idx+1 }.toMap()

    var sum = 0

    for (line in lines) {
        val compartmentOne = line.substring(0, line.length / 2).toSet().toString()
        val compartmentTwo = line.substring(line.length / 2).toSet().toString()
        sum += getPriorityScore(listOf(compartmentOne, compartmentTwo), letterScores)
    }
    println(sum)
}

fun getPriorityScore(compartments : List<String>, scores : Map<Char, Int>) : Int {
    return compartments[0].filter { it in compartments[1] }.map { scores[it] ?: 0 }.sum()
}


