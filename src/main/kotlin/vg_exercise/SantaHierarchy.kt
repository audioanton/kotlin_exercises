package vg_exercise

class Worker(private val name : String) {
    val subordinates: MutableList<Worker> = mutableListOf()

    fun getAllSubordinates() : List<String> {
        tailrec fun sumSubordinates(workers : List<Worker>, allSubordinates : List<Worker>) : List<String> {
            return if (workers.isEmpty()) allSubordinates.map { it.name }
            else {
                val subordinates: MutableList<Worker> = mutableListOf()
                workers.forEach { subordinates.addAll(it.subordinates) }
                return sumSubordinates(subordinates, allSubordinates + subordinates)
            }
        }
        return sumSubordinates(listOf(this), emptyList())
    }

    fun addSubordinatesByList(workers : List<Worker>) = subordinates.addAll(workers)

    fun findWorker(name : String, workers : List<Worker>) : Worker? {
        if (workers.isEmpty())
            return null
        val found = workers.filter { it.name == name }
        return if (found.size == 1) found[0]
        else findWorker(name, workers.flatMap { it.subordinates})
    }
}


fun main() {

    val names = listOf("Tomten", "Glader", "Butter", "Tröger", "Trötter", "Blyger", "Rådjuret", "Nyckelpigan", "Haren", "Räven",
                        "Skumtomten", "Gråsuggan", "Myran", "Bladlusen")

    val santa = Worker(names[0])
    santa.addSubordinatesByList(listOf(Worker(names[1]), Worker(names[2])))
    santa.findWorker(names[1], santa.subordinates)?.addSubordinatesByList(
        listOf(Worker(names[3]), Worker(names[4]), Worker(names[5]))
    )
    santa.findWorker(names[2], santa.subordinates)?.addSubordinatesByList(
        listOf(Worker(names[6]), Worker(names[7]), Worker(names[8]), Worker(names[9]))
    )
    santa.findWorker(names[4], santa.subordinates)?.subordinates?.add(Worker(names[10]))
    santa.findWorker(names[9], santa.subordinates)?.addSubordinatesByList(
        listOf(Worker(names[11]), Worker(names[12]))
    )
    santa.findWorker(names[12], santa.subordinates)?.subordinates?.add(Worker(names.last()))

    fun appLoop() {
        while (true) {
            println("Name? (e to exit)")
            val name = readln()
            if (name == "e")
                break
            val worker = santa.findWorker(name, listOf(santa))
            if (worker == null) println("No worker named $name found.\n")
            else {
                val subordinates = worker.getAllSubordinates()
                if (subordinates.isEmpty()) println("$name: [0]\n")
                else println(subordinates.joinToString(", ", "$name has ${subordinates.size} subordinates.\n", "\n"))
            }
        }
    }

    fun justDoIt() {
        println("Subordinates:")
        for (name in names) {
            val subordinates = santa.findWorker(name, listOf(santa))?.getAllSubordinates()
            if (subordinates?.isEmpty() == true)
                println("${name} [0]")
            else
                println(subordinates?.joinToString (", ", "${name} [${subordinates.size}]\n    "))
        }
    }

//    appLoop()
    justDoIt()

}