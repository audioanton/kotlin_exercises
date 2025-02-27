package advent_of_code_2022_day_6

import java.io.File

fun main() {
    val test1 = "mjqjpqmgbljsphdztnvjfqwrcgsmlb" // 7
    val test2 = "bvwbjplbgvbhsrlpgdmjqwftvncz" // 5
    val test3 = "nppdvjthqldpwncqszvftbrmjlhg" // 6
    val test4 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" // 10
    val test5 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" // 11

    val l = listOf(test1,test2,test3,test4,test5)

    val data : List<String> = File("src/main/kotlin/aoc_1/test_input.txt").readLines()

//    l.forEach { println(findMarker(it)) }

    println(findMarker(data[0]))
}


fun findMarker(subroutine : String) : Int {
    fun findMarkerRecursive(start : Int, accumulator : Int, str : String) : Int {
        return if (start+4 >= str.length ) -1
        else if (str.subSequence(start, start+4).toSet().size == 4) accumulator+1
        else findMarkerRecursive(start+1, start+4, str)
    }
    return findMarkerRecursive(0, 0, subroutine)
}