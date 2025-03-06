package exercise_2

class Worker(val name : String, val subordinates : List<Worker> = listOf()) { // default empty list

    // property getter (todd ginsburg) - inte konstant värde, utan en beräkning som utförs varje gång den kallas på.
    // skaffar alla underordnade för denna arbetare, sedan en utplattad lista av dess underordnades underordnade som i sin tur kallar på sina underordnades osv...
    // slutar när det inte finns fler underordnade
    val allSubordinates get() : List<String> = subordinates.map { it.name } + subordinates.flatMap { it.allSubordinates }

    tailrec fun collectAllSubordinates(workers: List<Worker>, allSubordinates : List<Worker>): List<String> {
            return if (workers.isEmpty()) allSubordinates.map { it.name }
            else {
                val flatSubordinates = workers.flatMap { it.subordinates }
                return collectAllSubordinates(flatSubordinates, allSubordinates + flatSubordinates)
            }
    }

    fun findWorker(name: String, subordinates: List<Worker>): Worker? {
        return if (this.name == name) this
        else if (subordinates.isEmpty()) null
        else subordinates.find { it.name == name } ?: findWorker(name, subordinates.flatMap { it.subordinates })
    }

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