package advent_of_code_2022_day_7_helped

import java.io.File

class Folder(val folderName: String) {
    val subFolders = mutableListOf<Folder>()
    var sizeAllFiles = 0

    fun changeFolder(folderName: String): Folder {
        val folder = subFolders.find { it.folderName == folderName }
        return if (folder != null) folder else {
            subFolders.add(Folder(folderName))
            return subFolders.last()
        }
    }

    fun addFile(fileSize: Int) { sizeAllFiles += fileSize }

    fun getFolderSize() : Int = sizeAllFiles + subFolders.sumOf { it.getFolderSize() }

}

fun createFolderTree(input : List<String>): Folder {
    val functionStack = ArrayDeque<Folder>()

    input.forEach { row ->
        when {
            (row == "$ ls") or row.startsWith("dir") -> {}

            row == "$ cd .." -> {
                if (functionStack.first().folderName != "/") functionStack.removeFirst()
            }

            row.startsWith("$ cd") -> {
                val folderName = row.substringAfter("cd ")
                if (functionStack.isEmpty()) functionStack.add(Folder(folderName))
                else functionStack.addFirst(functionStack.first().changeFolder(folderName))
            }

            else -> {
                val fileSize = row.substringBefore(" ").toInt()
                functionStack.first().addFile(fileSize)
            }
        }
    }
    return functionStack.last()
}

fun getFoldersFiltered(folder : Folder, predicate : (Folder) -> Boolean) : List<Folder> {
    return folder.subFolders.filter(predicate) +
            folder.subFolders.flatMap { getFoldersFiltered( it, predicate) }
}


fun main() {
    val rows : List<String> = File("src/main/kotlin/advent_of_code_2022_day_7/input.txt").readLines()
    val mainFolder = createFolderTree(rows)

    val spaceRemaining = 70_000_000 - mainFolder.getFolderSize() // underscores _ help keep track of numbers sizes
    val spaceNeeded = 30_000_000 - spaceRemaining

    val answerPartOne = getFoldersFiltered(mainFolder) { f -> f.getFolderSize() <= 100000 }.sumOf { it.getFolderSize() }
    val answerPartTwo = getFoldersFiltered(mainFolder) { it.getFolderSize() >= spaceNeeded }.minByOrNull { it.getFolderSize() }?.getFolderSize()

//    minBy() and maxBy() along with other functions replace reduce, min(), max() in Java
//    using functions instead of vals like me is more functional style programming, reusable and flexible

    println(answerPartOne)
    println(answerPartTwo)
}