package exercise_2

class Worker(val name : String) {
    val subordinates: MutableList<Worker> = mutableListOf()

    val allSubordinates get() : List<String> = subordinates.map { it.name } + subordinates.flatMap { it.allSubordinates }

    fun collectAllSubordinates(): List<String> {
        tailrec fun collectSubordinates(workers: List<Worker>, allSubordinates: List<Worker>): List<String> {
            return if (workers.isEmpty()) allSubordinates.map { it.name }
            else {
                val flatSubordinates = workers.flatMap { it.subordinates }
                return collectSubordinates(flatSubordinates, allSubordinates + flatSubordinates)
            }
        }
        return collectSubordinates(listOf(this), emptyList())
    }

    private fun addSubordinatesByList(workers: List<Worker>) = subordinates.addAll(workers)

    fun findWorker(name: String, workers: List<Worker>): Worker? {
        return if (this.name == name) this else {
            if (workers.isEmpty())
                return null
            val found = workers.filter { it.name == name }
            return if (found.size == 1) found[0]
            else findWorker(name, workers.flatMap { it.subordinates })
        }
    }

    fun initializeHierarchy(names: List<String>) {
        addSubordinatesByList(listOf(Worker(names[0]), Worker(names[1])))
        findWorker(names[0], subordinates)?.addSubordinatesByList(
            listOf(Worker(names[2]), Worker(names[3]), Worker(names[4]))
        )
        findWorker(names[1], subordinates)?.addSubordinatesByList(
            listOf(Worker(names[5]), Worker(names[6]), Worker(names[7]), Worker(names[8]))
        )
        findWorker(names[3], subordinates)?.subordinates?.add(Worker(names[9]))
        findWorker(names[8], subordinates)?.addSubordinatesByList(
            listOf(Worker(names[10]), Worker(names[11]))
        )
        findWorker(names[11], subordinates)?.subordinates?.add(Worker(names[12]))
        findWorker(names[9], subordinates)?.subordinates?.add(Worker(names.last()))
    }
}


fun main() {

    val names = listOf("Glader", "Butter", "Tröger", "Trötter", "Blyger", "Rådjuret", "Nyckelpigan", "Haren", "Räven",
        "Skumtomten", "Gråsuggan", "Myran", "Bladlusen", "Dammråttan")

    val santa = Worker("Tomten").apply { initializeHierarchy(names) }

    fun appLoop(santa : Worker) {
        while (true) {
            println("Name? (e to exit)")
            val name = readln()
            if (name == "e")
                break
            val worker = santa.findWorker(name, listOf(santa))
            if (worker == null) println("No worker named $name found.\n")
            else {
                val subordinates = worker.allSubordinates
                if (subordinates.isEmpty()) println("$name: [0]\n")
                else println(subordinates.joinToString(", ", "$name has ${subordinates.size} subordinates.\n", "\n"))
            }
        }
    }

    fun justDoIt(names : List<String>, santa : Worker) {
        println("${santa.name}'s subordinates:")
        for (name in names) {
            val subordinates = santa.findWorker(name, listOf(santa))?.allSubordinates
            if (subordinates?.isEmpty() == true)
                println("${name} [0]")
            else
                println(subordinates?.joinToString (", ", "${name} [${subordinates.size}]\n    "))
        }
    }

    //    appLoop(santa)
    justDoIt(names, santa)
}