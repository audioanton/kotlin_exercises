package exercise_2

class Worker(val name : String, val subordinates : List<Worker> = listOf()) { // default empty list

    val allSubordinates get() : List<String> = subordinates.map { it.name } + subordinates.flatMap { it.allSubordinates }

    tailrec fun collectAllSubordinates(workers: List<Worker>, allSubordinates : List<Worker>): List<String> {
            return if (workers.isEmpty()) allSubordinates.map { it.name }
            else {
                val flatSubordinates = workers.flatMap { it.subordinates }
                return collectAllSubordinates(flatSubordinates, allSubordinates + flatSubordinates)
            }
    }

    fun findWorker(name: String, workers: List<Worker>): Worker? =
        if (this.name == name) this else workers.find { it.name == name } ?: findWorker(name, workers.flatMap { it.subordinates })

    // instead of static function
    companion object {
        fun getSanta(): Worker {
            val myran = Worker("Myran", listOf(Worker("Bladlusen")))
            val räven = Worker("Räven", listOf(Worker("Gråsuggan"), myran))
            val skumtomten = Worker("Skumtomten", listOf(Worker("Dammråttan")))
            val trötter = Worker("Trötter", listOf(skumtomten))
            val butter = Worker("Butter", listOf(Worker("Rådjuret"), Worker("Nyckelpigan"), Worker("Haren"), räven))
            val glader = Worker("Glader", listOf(Worker("Tröger"), trötter, Worker("Blyger")))
            return Worker("Tomten", listOf(glader, butter))
        }
    }
}

fun main() {
    val santa = Worker.getSanta()

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