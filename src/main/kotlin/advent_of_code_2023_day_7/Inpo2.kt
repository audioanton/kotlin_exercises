package advent_of_code_2023_day_7

fun main() {

//    https://www.reddit.com/r/adventofcode/comments/18cnzbm/comment/kcc1sk2/

    // algorithm:
//    create data class hand, data class helps with comparing
//    for each line, save hand 'multiplicities' ordered by highest ocurrence, convert all card values to int and save their values ordered, save bid value
//    sort all hands according to their 'rank' - which is a combination of their multiplicities and card values, lowest rank first
//    multiply all hands bid value by their rank value
//    sum all the results


    fun answer(lines: List<String>) {

        data class Hand(val cards: List<Int>, val groups: List<Int>, val bid: Int)

        val values = listOf('T', 'J', 'Q', 'K', 'A') // all 'cards' from lowest to highest score
        lines
            .map { it.split(" ") }.map { (text, bid) ->
                val cards = // if char is in list, add 10 points to it and it's index, converting to Int, making easy to compare
                    text.map { card -> values.indexOf(card)
                        .let { if (it > -1) it + 10 else card.digitToInt() } } // if not in list, it is a number - and numbers have their value as point
                val groups = cards.groupBy { it } .map { it.value.size }.sortedByDescending { it }  // listing out how many of each character, digit or letter occurs.. sorting by highest number of ocurrences first
                Hand(cards, groups, bid.toInt()) // creating hand object with the three datas needed, then taking the bid value as int
            }  // the whole .map { lambda } with two parameters (text, bid) -> becomes a list of Hand objects
            .sortedWith( // taking in lambdas as functions by how to compare the hand objects in list
                compareBy(
                    { it.groups[0] }, // sorted sequentially, if one functions marks them not equal, the sorting returns
                    { it.groups[1] }, // there is a maximum of two kinds of multiples, full house or 2 pairs, no need to check more
                    { it.cards[0] }, // if no difference - checking card values by order of appearance in hand
                    { it.cards[1] },
                    { it.cards[2] },
                    { it.cards[3] },
                    { it.cards[4] })
            )
            .mapIndexed { index, hand -> (index + 1) * hand.bid } // mapping to Int-list using the index to transform the values
            .sum() // summing all values in intlist, getting answer.
    }
}
