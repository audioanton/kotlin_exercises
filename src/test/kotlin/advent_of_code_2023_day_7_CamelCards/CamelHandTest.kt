package advent_of_code_2023_day_7_CamelCards

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class CamelHandTest {

@Test
 fun compareToTest() {
 val high = CamelHand(listOf(10,9,8,7), listOf(3,2), 456)
 val midHigh = CamelHand(listOf(10,8,8,7), listOf(3,2), 456)
 val middle = CamelHand(listOf(10,9,8,7), listOf(2,2,1), 456)
 val low = CamelHand(listOf(9,9,8,7), listOf(2,1,1,1), 456)
 val lowCopy = CamelHand(listOf(9,9,8,7), listOf(2,1,1,1), 456)


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