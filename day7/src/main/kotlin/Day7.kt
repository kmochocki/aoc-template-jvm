fun main() {
    println(part1())
    println(part2())
}

val operationsPart1: List<(Long, Long) -> Long> = listOf(
    { a: Long, b: Long -> a + b },
    { a: Long, b: Long -> a * b },
)

fun part1(): Any {
    return input.splitToSequence("\n")
        .filter { it.isNotBlank() }
        .mapNotNull {
            val (testValueString, numbersString) = it.split(":")
            val test = testValueString.toLong()
            val numbers = numbersString.split(" ")
                .filter(String::isNotBlank)
                .map(String::toLong)
            if (calculatePart1(numbers).any { result -> result == test }) {
                test
            } else {
                null
            }
        }
        .sum()
}

private fun calculatePart1(rest: List<Long>): List<Long> {
    return if (rest.size > 1) {
        calculatePart1(rest.take(rest.size - 1))
            .flatMap { result ->
                operationsPart1.map { operation ->
                    operation(result, rest.last())
                }
            }
    } else {
        listOf(rest.first())
    }
}

val operationsPart2: List<(Long, Long) -> Long> = listOf(
    { a: Long, b: Long -> a + b },
    { a: Long, b: Long -> a * b },
    { a: Long, b: Long -> (a.toString() + b.toString()).toLong() },
)

fun part2(): Any {
    return input.splitToSequence("\n")
        .filter { it.isNotBlank() }
        .mapNotNull {
            val (testValueString, numbersString) = it.split(":")
            val test = testValueString.toLong()
            val numbers = numbersString.split(" ")
                .filter(String::isNotBlank)
                .map(String::toLong)
            if (calculatePart2(numbers).any { result -> result == test }) {
                test
            } else {
                null
            }
        }
        .sum()
}

private fun calculatePart2(rest: List<Long>): List<Long> {
    return if (rest.size > 1) {
        calculatePart2(rest.take(rest.size - 1))
            .flatMap { result ->
                operationsPart2.map { operation ->
                    operation(result, rest.last())
                }
            }
    } else {
        listOf(rest.first())
    }
}

private val input: String by lazy {readInput()}

private fun readInput(): String {
    return object {}.javaClass.getResource("Day7.input").readText()
}
