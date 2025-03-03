package advent_of_code_2023_day_7

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class ParsedHandTest {

@Test
 fun compareToTest() {
 val high = ParsedHand(listOf(10,9,8,7), listOf(3,2), 456)
 val midHigh = ParsedHand(listOf(10,8,8,7), listOf(3,2), 456)
 val middle = ParsedHand(listOf(10,9,8,7), listOf(2,2,1), 456)
 val low = ParsedHand(listOf(9,9,8,7), listOf(2,1,1,1), 456)
 val lowCopy = ParsedHand(listOf(9,9,8,7), listOf(2,1,1,1), 456)


 assertEquals(1, high.compareTo(low))
 assertEquals(1, high.compareTo(middle))
 assertEquals(1, high.compareTo(midHigh))
 assertEquals(-1, low.compareTo(high))
 assertEquals(-1, low.compareTo(midHigh))
 assertEquals(-1, low.compareTo(middle))
 assertEquals(1, middle.compareTo(low))
 assertEquals(-1, middle.compareTo(high))
 assertEquals(0, low.compareTo(lowCopy))

 }
}