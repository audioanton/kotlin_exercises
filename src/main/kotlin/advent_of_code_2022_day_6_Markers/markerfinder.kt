package advent_of_code_2022_day_6_Markers

import java.io.File

fun main() {
    val data : List<String> = File("src/main/kotlin/advent_of_code_2022_day_6_Markers/test_input.txt").readLines()

    // part 1
    println(findMarker(data[0], 4))

    // part 2
    println(findMarker(data[0], 14))
}

fun findMarker(subroutine : String, routineLength : Int) : Int {
    tailrec fun findMarkerRecursive(start : Int, str : String) : Int {
        return if (start+routineLength >= str.length ) -1
        else if (str.subSequence(start, start+routineLength).toSet().size == routineLength) start+routineLength
        else findMarkerRecursive(start+1,  str)
    }
    return findMarkerRecursive(0, subroutine)
}