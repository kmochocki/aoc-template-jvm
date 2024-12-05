fun main() {
    println(part1())
    println(part2())
}

val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()

fun part1(): Any {
    return calculateMuls(input)
}

fun part2(): Any {
    val splitDonts = input.split("don't()")
    val subList = splitDonts.subList(1, splitDonts.size)
        .flatMap {
            val splitDos = it.split("do()")
            splitDos.subList(1, splitDos.size)
        }
        .joinToString("") { it }

    return calculateMuls(splitDonts.first() + subList)
}

private fun calculateMuls(input: String): Int {
    return regex.findAll(input)
        .map { it.groupValues }
        .map { it[1].toInt() * it[2].toInt() }
        .sum()
}

private val input: String by lazy {readInput()}

private fun readInput(): String {
    return object {}.javaClass.getResource("Day3.input").readText()
}
