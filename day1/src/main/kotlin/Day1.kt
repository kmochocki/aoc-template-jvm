import kotlin.math.absoluteValue

fun main() {
    println(part1())
    println(part2())
}

fun part1(): Any {
    val (firstList, secondList) = inputAsLists()
    val firstSorted = firstList.asSequence()
        .map { it.value }
        .sorted()
    val secondSorted = secondList.asSequence()
        .map { it.value }
        .sorted()
    val result = firstSorted.zip(secondSorted) { first, second -> first - second }
        .map { it.absoluteValue }
        .sum()
    return result
}

fun part2(): Any {
    val (firstList, secondList) = inputAsLists()
    val counts = secondList.asSequence()
        .map { it.value }
        .groupingBy { it }
        .eachCount()
    return firstList.asSequence()
        .map { it.value }
        .map { counts[it]?.times(it) ?: 0 }
        .sum()
}

private fun inputAsLists() = input.splitToSequence("\n")
    .filter { it.isNotBlank() }
    .flatMap { it.splitToSequence("   ") }
    .map { it.toInt() }
    .withIndex()
    .partition { it.index % 2 == 0 }

private val input: String by lazy {readInput()}

private fun readInput(): String {
    return object {}.javaClass.getResource("Day1.input").readText()
}
