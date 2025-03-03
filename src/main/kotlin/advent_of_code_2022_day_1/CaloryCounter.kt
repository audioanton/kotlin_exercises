package advent_of_code_2022_day_1

import java.io.File

fun main () {

    val lines = File("src/main/kotlin/advent_of_code_2022_day_1/input.txt").readLines()

    fun getMaxCalories(input : String) : Int {
        tailrec fun maxCalories(remaining : String, summed : MutableList<Int>) : Int {
            return if (remaining == "-") summed.max()
            else {
                summed.add( remaining.substringBefore(" /").split(" ").sumOf { it.replace(" ", "").toInt() } )
                val remain = remaining.substringAfter("/ ")
                maxCalories(remain, summed)
            }
        }
        return maxCalories(input, mutableListOf())
    }

    println(getMaxCalories(lines.joinToString(separator = " ", postfix = " / -") { it.ifEmpty { "/" } }))
}