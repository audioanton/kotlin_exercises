package advent_of_code_2022_day_2_RPS
import java.io.File

class StrategyGuideRPS(private val moveValues: Map<String, Int> = mapOf("A" to 1, "B" to 2, "C" to 3)) {

    private fun solve(lines: List<String>, operate : (String) -> (Int)) : Int {
        val summed : MutableList<Int> = mutableListOf()
        lines.forEach { summed.add(operate(it)) }
        return summed.sum()
    }

    fun solvePartOne(lines: List<String>) : Int = solve(lines) { line ->
        val conversions = mapOf("X" to "A", "Y" to "B", "Z" to "C")
        val round: List<String> = line.split(" ").map { conversions[it] ?: it}
        getRoundResult(round) + moveValues[round[1]]!!
    }

    fun solvePartTwo(lines: List<String>) : Int = solve(lines) { line ->
        val results = mapOf("X" to 0, "Y" to 3, "Z" to 6)
        val round =  line.split(" ")
        val result: Int = results[round.last()] ?: 0
        result + getMoveValue(round[0] to result)
    }

    fun getMoveValue(result: Pair<String, Int>): Int {
        val pointsLoss: Map<String, Int> = mapOf("A" to 3, "B" to 1, "C" to 2)
        val pointsWin: Map<String, Int> = mapOf("A" to 2, "B" to 3, "C" to 1)
        return when (result.second) {
            3 -> moveValues[result.first] ?: 0
            6 -> pointsWin[result.first] ?: 0
            else -> pointsLoss[result.first] ?: 0
        }
    }

    fun getRoundResult(round : List<String>) : Int {
        val beats : Map<String, String> = mapOf("A" to "C", "B" to "A", "C" to "B")
        return if (beats[round[0]] == round[1]) 0 else if (round[0] == round[1]) 3 else 6
    }
}

fun main() {
    val lines = File("src/main/kotlin/advent_of_code_2022_day_2/input.txt").readLines()
    val guide = StrategyGuideRPS()

    println(guide.solvePartOne(lines))
    println(guide.solvePartTwo(lines))

}