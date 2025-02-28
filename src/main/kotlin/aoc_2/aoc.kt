package aoc_2

fun main () {
    val test = "30373 25512 65332 33549 35390".split(" ")

    // 16

//    val intList : Unit = test.map { s -> s.toCharArray().filter { it.isDigit() }.map { it.digitToInt() }





    fun onTheEdge(list : List<String>) : Int {
        val topBottomRow = list[0].length + list[list.lastIndex].length
        return topBottomRow + ((list.size-2) * 2)
    }
//
//    fun visibleLeftRight(str : String, index : Int) : Int {
//        for (c in str)
//
//    }
    println(onTheEdge(test))

//    println(test.size)
    // visibleUpDown()
    // visibleLeftRight()
    // onTheEdge()
}
