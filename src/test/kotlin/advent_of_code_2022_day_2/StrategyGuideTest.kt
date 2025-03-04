package advent_of_code_2022_day_2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class StrategyGuideTest {

  val guide = StrategyGuide()
  val lines = listOf("A Y", "B X", "C Z")

@Test
 fun getRoundResult() {
 assertEquals(0, guide.getRoundResult(listOf("A","C")))
 assertEquals(3, guide.getRoundResult(listOf("A","A")))
 assertEquals(6, guide.getRoundResult(listOf("A","B")))
 assertEquals(6, guide.getRoundResult(listOf("B","C")))
 assertEquals(3, guide.getRoundResult(listOf("B","B")))
 assertEquals(0, guide.getRoundResult(listOf("B","A")))
 assertEquals(6, guide.getRoundResult(listOf("C","A")))
 assertEquals(3, guide.getRoundResult(listOf("C","C")))
 assertEquals(0, guide.getRoundResult(listOf("C","B")))
 }

  @Test
  fun solvePartOne() {
   assertEquals(15, guide.solvePartOne(lines))
  }

  @Test
  fun getSymbolValue() {
   assertEquals(1, guide.getSymbolValue("A" to 3))
   assertEquals(2, guide.getSymbolValue("A" to 6))
   assertEquals(3, guide.getSymbolValue("A" to 0))
   assertEquals(2, guide.getSymbolValue("B" to 3))
   assertEquals(3, guide.getSymbolValue("B" to 6))
   assertEquals(1, guide.getSymbolValue("B" to 0))
   assertEquals(3, guide.getSymbolValue("C" to 3))
   assertEquals(1, guide.getSymbolValue("C" to 6))
   assertEquals(2, guide.getSymbolValue("C" to 0))
  }

  @Test
  fun solvePartTwo() {
   assertEquals(12, guide.solvePartTwo(lines))
  }
 }