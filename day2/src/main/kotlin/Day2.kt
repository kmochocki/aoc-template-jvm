fun main() {
    println(part1())
    println(part2())
}

fun part1(): Any {
    return input.splitToSequence("\n")
        .filter { record -> record.isNotBlank() }
        .map { record ->
            val differences = record.splitToSequence(" ")
                .map { it.toInt() }
                .zipWithNext()
                .map { it.first - it.second }
            differences.all { it in 1..3 } or differences.all { it in -3..-1 }
        }
        .filter { it }
        .count()
}

fun part2(): Any {
    return input.splitToSequence("\n")
        .filter { record -> record.isNotBlank() }
        .map { record ->
            val report = record.splitToSequence(" ")
                .map { it.toInt() }
                .withIndex()
            var isSafe = false
            for (i in 0 until report.count()) {
                val differences = report.filter { it.index != i }
                    .map { it.value }
                    .zipWithNext()
                    .map { it.first - it.second }
                isSafe = differences.all { it in 1..3 } or differences.all { it in -3..-1 }
                if (isSafe) {
                    break
                }
            }
            isSafe
        }
        .filter { it }
        .count()
}

private val input: String by lazy {readInput()}

private fun readInput(): String {
    return object {}.javaClass.getResource("Day2.input").readText()
}
