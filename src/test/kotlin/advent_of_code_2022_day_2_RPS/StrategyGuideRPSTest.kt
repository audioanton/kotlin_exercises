package advent_of_code_2022_day_2_RPS

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class StrategyGuideRPSTest {

  val guide = StrategyGuideRPS()
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
  fun getMoveValue() {
   assertEquals(1, guide.getMoveValue("A" to 3))
   assertEquals(2, guide.getMoveValue("A" to 6))
   assertEquals(3, guide.getMoveValue("A" to 0))
   assertEquals(2, guide.getMoveValue("B" to 3))
   assertEquals(3, guide.getMoveValue("B" to 6))
   assertEquals(1, guide.getMoveValue("B" to 0))
   assertEquals(3, guide.getMoveValue("C" to 3))
   assertEquals(1, guide.getMoveValue("C" to 6))
   assertEquals(2, guide.getMoveValue("C" to 0))
  }

  @Test
  fun solvePartTwo() {
   assertEquals(12, guide.solvePartTwo(lines))
  }
 }