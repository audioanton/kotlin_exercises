package advent_of_code_2022_day_1_Calories

import java.io.File

fun main () {

    val lines = File("src/main/kotlin/advent_of_code_2022_day_1_Calories/input.txt").readLines()

    // imperative
    val summed : MutableList<Int> = mutableListOf(0)
    for (line in lines)
        if (line.isBlank())
            summed.add(0)
        else
            summed[summed.size-1] = summed.last() + line.replace(" ", "").toInt()

    // part one
    println(summed.max())
    // part two
    println(summed.sortedDescending().subList(0, 3).sum())
}