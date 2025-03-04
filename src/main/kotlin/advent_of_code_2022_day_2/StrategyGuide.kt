package advent_of_code_2022_day_2
import java.io.File

class StrategyGuide {

    fun solvePartOne(lines : List<String>) : Int {
        val points : Map<String, Int> = mapOf("A" to 1, "B" to 2, "C" to 3)
        val replacements = mapOf("X" to "A", "Y" to "B", "Z" to "C")

        val summed : MutableList<Int> = mutableListOf()

        for (line in lines) {
            val round: List<String> = line.split(" ").map { replacements[it] ?: it}
            summed.add(getRoundResult(round) + points[round[1]]!!)
            }
        return summed.sum()
    }

    fun solvePartTwo(lines: List<String>) : Int {
        val replacements = mapOf("X" to 0, "Y" to 3, "Z" to 6)
        val summed : MutableList<Int> = mutableListOf()

        for (line in lines) {
            val round: List<String> = line.split(" ")
            val result: Int = round.map { replacements[it] ?: 0 }.last()
            summed.add(result + getSymbolValue(round[0] to result)!!)
        }
        return summed.sum()
    }

    fun getRoundResult(round : List<String>) : Int {
        val beats : Map<String, String> = mapOf(Pair("A", "C"), Pair("B", "A"), Pair("C", "B"))
        return if (beats[round[0]] == round[1]) 0 else if (round[0] == round[1]) 3 else 6
    }

    fun getSymbolValue(result: Pair<String, Int>) : Int? {
        val points : Map<String, Int> = mapOf(Pair("A", 1), Pair("B", 2), Pair("C", 3))
        val pointsLoss : Map<String, Int> = mapOf("A" to 3, "B" to 1, "C" to 2)
        val pointsWin : Map<String, Int> = mapOf("A" to 2, "B" to 3, "C" to 1)
        return when (result.second) {
            3 -> points[result.first]
            6 -> pointsWin[result.first]
            else -> pointsLoss[result.first]
        }
    }
}

fun main() {
    val lines = File("src/main/kotlin/advent_of_code_2022_day_2/input.txt").readLines()

    val guide = StrategyGuide()

    println(guide.solvePartOne(lines))
    println(guide.solvePartTwo(lines))
}