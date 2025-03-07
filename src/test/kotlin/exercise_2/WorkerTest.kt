package exercise_2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WorkerTest {

 val santa = Worker.getSanta()

 val subTomten = listOf("Tröger", "Trötter", "Blyger", "Dammråttan", "Skumtomten", "Glader", "Butter", "Rådjuret", "Nyckelpigan", "Haren", "Räven", "Gråsuggan", "Myran", "Bladlusen")
 val subGlader = listOf("Tröger", "Trötter", "Blyger", "Dammråttan", "Skumtomten")
 val subButter = listOf("Rådjuret", "Nyckelpigan", "Haren", "Räven", "Gråsuggan", "Myran", "Bladlusen")
 val subTrötter = listOf("Dammråttan", "Skumtomten")
 val subSkumtomten = listOf("Dammråttan")
 val subRäven = listOf("Gråsuggan", "Myran", "Bladlusen")
 val subMyran = listOf("Bladlusen")

 @Test
 fun subordinatesBladlusen() {
  val worker = santa.findWorker("Bladlusen", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertEquals(0, subordinates!!.size)
 }

 @Test
 fun subordinatesTröger() {
  val worker = santa.findWorker("Tröger", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertEquals(0, subordinates!!.size)
 }

 @Test
 fun subordinatesBlyger() {
  val worker = santa.findWorker("Blyger", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertEquals(0, subordinates!!.size)
 }

 @Test
 fun subordinatesDammråttan() {
  val worker = santa.findWorker("Dammråttan", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertEquals(0, subordinates!!.size)
 }

 @Test
 fun subordinatesGråsuggan() {
  val worker = santa.findWorker("Gråsuggan", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertEquals(0, subordinates!!.size)
 }

 @Test
 fun subordinatesHaren() {
  val worker = santa.findWorker("Haren", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertEquals(0, subordinates!!.size)
 }

 @Test
 fun subordinatesNyckelpigan() {
  val worker = santa.findWorker("Nyckelpigan", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertEquals(0, subordinates!!.size)
 }

 @Test
 fun subordinatesRådjuret() {
  val worker = santa.findWorker("Rådjuret", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertEquals(0, subordinates!!.size)
 }

 @Test
 fun subordinatesMyran() {
  val worker = santa.findWorker("Myran", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertTrue(subordinates!!.containsAll(subMyran))
  assertEquals(subMyran.size, subordinates!!.size)
  assertFalse(subordinates.contains(worker.name))
 }

 @Test
 fun subordinatesRäven() {
  val worker = santa.findWorker("Räven", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertEquals(subRäven.size, subordinates!!.size)
  assertTrue(subordinates.containsAll(subRäven))
  assertFalse(subordinates.contains(worker.name))
 }

 @Test
 fun subordinatesSkumtomten() {
  val worker = santa.findWorker("Skumtomten", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertTrue(subordinates!!.containsAll(subSkumtomten))
  assertEquals(subSkumtomten.size, subordinates.size)
  assertFalse(subordinates.contains(worker.name))
 }

 @Test
 fun subordinatesTrötter() {
  val worker = santa.findWorker("Trötter", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertTrue(subordinates!!.containsAll(subTrötter))
  assertEquals(subTrötter.size, subordinates.size)
  assertFalse(subordinates.contains(worker.name))
 }

 @Test
 fun subordinatesButter() {
  val worker = santa.findWorker("Butter", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertTrue(subordinates!!.containsAll(subButter))
  assertEquals(subButter.size, subordinates.size)
  assertFalse(subordinates.contains(worker.name))
 }

 @Test
 fun subordinatesGlader() {
  val worker = santa.findWorker("Glader", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertTrue(subordinates!!.containsAll(subGlader))
  assertEquals(subGlader.size, subordinates.size)
  assertFalse(subordinates.contains(worker.name))
 }

 @Test
 fun subordinatesTomten() {
  val worker = santa.findWorker("Tomten", santa.subordinates)
  val subordinates = worker?.allSubordinates
  assertNotNull(subordinates)
  assertTrue(subordinates!!.containsAll(subTomten))
  assertEquals(subTomten.size, subordinates.size)
  assertFalse(subordinates.contains(worker.name))
 }

 @Test
 fun collectAllSubordinatesTest() {
  val worker = santa.findWorker("Tomten", santa.subordinates)
  val subordinates = worker?.collectAllSubordinates(listOf(worker), emptyList())
  assertNotNull(subordinates)
  assertTrue(subordinates!!.containsAll(subTomten))
  assertEquals(subTomten.size, subordinates.size)
  assertFalse(subordinates.contains(worker.name))

  val newWorker = santa.findWorker("Glader", santa.subordinates)
  val newSubs = newWorker?.collectAllSubordinates(listOf(newWorker), emptyList())
  assertNotNull(newSubs)
  assertTrue(subordinates.containsAll(subGlader))
  assertEquals(subGlader.size, newSubs!!.size)
  assertFalse (newSubs.contains(newWorker.name))
 }

 @Test
 fun findWorkerTest() {
  val worker = santa.findWorker("Greger", santa.subordinates)
  assertNull(worker)
 }
}
