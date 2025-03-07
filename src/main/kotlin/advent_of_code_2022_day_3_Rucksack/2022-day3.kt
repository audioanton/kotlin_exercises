package advent_of_code_2022_day_3_Rucksack

//NOT MY CODE!!!!!!!

import java.io.File

// Study colleagues code https://github.com/JossanTorner/AOC-assignment/tree/master/src

fun getData() : MutableList<String> {
    var data = mutableListOf<String>()
    try{
//        data = File("src/day3_2022/data_day3_josefin").readLines().toMutableList()
    }
    catch (e: Exception){
        println(e.message)
    }
    return data
}

// Divide each line in two parts, find the one letter that appears in both parts and add the priority value of that letter to a sum
fun solvePartOne(input: List<String>) : Int {
    return input
        .asSequence()  // Förbättrar prestanda för stora datamängder
        .map { rucksack ->
            rucksack.take(rucksack.length / 2).toSet() intersect rucksack.drop(rucksack.length / 2).toSet()
        }
        .flatten()  // Plattar ut setet till en lista
        .sumOf { priorities[it] ?: 0 }  // Summerar prioriteringar, eller 0 om inte funnen
}

// Group input in chunks of 3 lines each, find the letter that appears in all three and add priority value of that letter to a sum
fun solvePartTwo(input: List<String>): Int {
    return input
        .asSequence()
        .chunked(3)
        .map { group ->
            group[0].toSet()
                .intersect(group[1].toSet())
                .intersect(group[2].toSet())
                .first()  // Hitta gemensam bokstav i alla 3 ransaksäckar
        }
        .sumOf { priorities[it] ?: 0 }  // Summera prioriteringar
}

// skapar en map som "parar ihop" varje bokstav med ett nummervärde vilket sedan hämtas i solvepart1functional.

val priorities = (('a'..'z').zip(1..26) + ('A'..'Z').zip(27..52)).toMap()

//{
//  a=1, b=2, c=3, d=4, e=5, f=6, g=7, h=8, i=9, j=10, k=11, l=12, m=13, n=14, o=15, p=16, q=17, r=18, s=19, t=20, u=21, v=22, w=23, x=24, y=25, z=26,
//  A=27, B=28, C=29, D=30, E=31, F=32, G=33, H=34, I=35, J=36, K=37, L=38, M=39, N=40, O=41, P=42, Q=43, R=44, S=45, T=46, U=47, V=48, W=49, X=50, Y=51, Z=52
//}

fun main(){
    println(solvePartOne(getData()))
    println(solvePartTwo(getData()))
}


// part 1, tailrec function vi gjort själv (ej till 100%)
fun partOneOwnSolution(list : MutableList<String>) : Long {
    tailrec fun accSum(index : Int, sum : Long) : Long{
        if (index >= list.size) return sum
        val currentWord = list[index]
        val middle = list[index].length / 2
        val wordDivided = arrayOf(currentWord.substring(0, middle), currentWord.substring(middle))
        val commonChars = wordDivided[0].toSet().intersect(wordDivided[1].toSet())

        val priorityNum = commonChars.sumOf {
            when (it) {
                in 'a'..'z' -> (it.code - 'a'.code + 1).toLong()
                in 'A'..'Z' -> (it.code - 'A'.code + 27).toLong()
                else -> 0
            }
        }
        return accSum(index+1, sum + priorityNum)
    }
    return accSum(0, 0)
}