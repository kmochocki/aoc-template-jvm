fun main() {
    println(part1())
    println(part2())
}

val operationsPart1: List<(Long, Long) -> Long> = listOf(
    { a: Long, b: Long -> a + b },
    { a: Long, b: Long -> a * b },
)

fun part1(): Any {
    return input.lineSequence()
        .filter { it.isNotBlank() }
        .mapNotNull {
            val (testValueString, numbersString) = it.split(":")
            val test = testValueString.toLong()
            val numbers = numbersString.splitToSequence(" ")
                .filter(String::isNotBlank)
                .map(String::toLong)
                .toList()
            if (calculatePart1(numbers, test)) test else null
        }
        .sum()
}

private fun calculatePart1(rest: List<Long>, target: Long): Boolean {
    return calculatePart1Inner(rest, target, 1, rest.first())
}

fun calculatePart1Inner(
    rest: List<Long>,
    target: Long,
    index: Int,
    current: Long
): Boolean {
    if (index == rest.size) return current == target

    val next = rest[index]
    for (operation in operationsPart1) {
        if (calculatePart1Inner(rest, target, index + 1, operation(current, next))) return true
    }

    return false
}

val operationsPart2: List<(Long, Long) -> Long> = listOf(
    { a: Long, b: Long -> a + b },
    { a: Long, b: Long -> a * b },
    { a: Long, b: Long -> (a.toString() + b.toString()).toLong() },
)

fun part2(): Any {
    return input.lineSequence()
        .filter { it.isNotBlank() }
        .mapNotNull {
            val (testValueString, numbersString) = it.split(":")
            val test = testValueString.toLong()
            val numbers = numbersString.splitToSequence(" ")
                .filter(String::isNotBlank)
                .map(String::toLong)
                .toList()
            if (calculatePart2(numbers, test)) test else null
        }
        .sum()
}

private fun calculatePart2(rest: List<Long>, target: Long): Boolean {
    return calculatePart2Inner(rest, target, 1, rest.first())
}

fun calculatePart2Inner(
    rest: List<Long>,
    target: Long,
    index: Int,
    current: Long
): Boolean {
    if (index == rest.size) return current == target

    val next = rest[index]
    for (operation in operationsPart2) {
        if (calculatePart2Inner(rest, target, index + 1, operation(current, next))) return true
    }

    return false
}

private val input: String by lazy {readInput()}

private fun readInput(): String {
    return object {}.javaClass.getResource("Day7.input").readText()
}
