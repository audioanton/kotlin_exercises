package advent_of_code_2022_day_6

import java.io.File

fun main() {
    val test1 = "mjqjpqmgbljsphdztnvjfqwrcgsmlb" // 7 - 19
    val test2 = "bvwbjplbgvbhsrlpgdmjqwftvncz" // 5 - 23
    val test3 = "nppdvjthqldpwncqszvftbrmjlhg" // 6 - 23
    val test4 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" // 10 - 29
    val test5 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" // 11 - 26

    val l = listOf(test1,test2,test3,test4,test5)

    val data : List<String> = File("src/main/kotlin/advent_of_code_2022_day_6/test_input.txt").readLines()

//    l.forEach { println(findMarker(it, 14)) }

    println(findMarker(data[0], 14))
}

fun findMarker(subroutine : String, routineLength : Int) : Int {
    fun findMarkerRecursive(start : Int, accumulator : Int, str : String) : Int {
        return if (start+routineLength >= str.length ) -1
        else if (str.subSequence(start, start+routineLength).toSet().size == routineLength) accumulator+1
        else findMarkerRecursive(start+1, start+routineLength, str)
    }
    return findMarkerRecursive(0, 0, subroutine)
}