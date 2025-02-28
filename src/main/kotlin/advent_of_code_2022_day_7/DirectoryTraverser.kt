package advent_of_code_2022_day_7

import java.io.File

class Directory(val name: String) {
    val subDirs: MutableMap<String, Directory> = mutableMapOf()
    private var sizeOfFiles = 0

    fun traverse(dirName: String): Directory = subDirs.getOrPut(dirName) { Directory(dirName) } // getOrPut() for VG exercise?

    val size: Int get() {
        val calcSize = sizeOfFiles + subDirs.values.sumOf { it.size }
        println("${this.name} : ${calcSize}")
        return calcSize
        }

    fun addFile(size: Int) {
        sizeOfFiles += size
    }
}

private fun parseInput(input: List<String>): Directory {
    val callStack = ArrayDeque<Directory>().apply { add(Directory("/")) } // use for VG uppgift??

    input.forEach { item ->
        when {
            (item == "$ ls") or item.startsWith("dir")  -> {
//                println("ls/dir - no operation. ITEM: $item")
            }

            item == "$ cd /" -> {
                println("cd - removing if not /. ITEM: $item ")
                callStack.removeIf { it.name != "/" }
            }

            item == "$ cd .." -> {
                println("cd.. removing dir from stack /. removing: ${callStack.first().name} ")
                callStack.removeFirst()
            }

            item.startsWith("$ cd") -> {
                val name = item.substringAfterLast(" ")
                println("cd string - adding or going to dir: ${name}")
                callStack.addFirst(callStack.first().traverse(name))
            }

            else -> {
                val size = item.substringBefore(" ").toInt()
                println("$item : adding filesize $size to ${callStack.first().name}")
                callStack.first().addFile(size)
            }
        }
    }
    return callStack.last()
}

fun main() {
    val data: List<String> = File("src/main/kotlin/advent_of_code_2022_day_7/testing.txt").readLines()

    val dir = parseInput(data)

    println(dir.size)

    val validDirs = find(dir) { it.size <= 100000 }
    println(validDirs.sumOf { it.size} )

//    get list of directory from dir.subdirectories where size <= 100000
}

fun find(directory: Directory, predicate: (Directory) -> Boolean) : List<Directory> {
    return directory.subDirs.values.filter(predicate) + directory.subDirs.values.flatMap  { find(it) { predicate.invoke(it)} }
}

