fun main() {
    println(part1())
    println(part2())
}

fun part1(): Any {
    val splitted = input.split("\\n\\n".toRegex())
    val rules = splitted[0].splitToSequence("\n")
        .map { it.split("|") }
        .map { it[0].toInt() to it[1].toInt() }
        .groupBy(Pair<Int, Int>::first) { it.second }
    return splitted[1].splitToSequence("\n")
        .filter { it.isNotBlank() }
        .map { updates ->
            updates.splitToSequence(",")
                .map { it.toInt() }
                .toList()
        }
        .filter { updates ->
            var isValid = true
            for (i in updates.size - 1 downTo 1) {
                val precedingUpdates = updates.take(i)
                isValid = rules[updates[i]]?.none { precedingUpdates.contains(it) } ?: false
                if (isValid.not()) {
                    break
                }
            }
            isValid
        }
        .map { updates -> updates[updates.size / 2] }
        .sum()
}

fun part2(): Any {
    val splitted = input.split("\\n\\n".toRegex())
    val rules = splitted[0].splitToSequence("\n")
        .map { it.split("|") }
        .map { it[0].toInt() to it[1].toInt() }
        .groupBy(Pair<Int, Int>::first) { it.second }
    return splitted[1].splitToSequence("\n")
        .filter { it.isNotBlank() }
        .map { updates ->
            updates.splitToSequence(",")
                .map { it.toInt() }
                .toList()
        }
        .filter { updates ->
            var isValid = true
            for (i in updates.size - 1 downTo 1) {
                val precedingUpdates = updates.take(i)
                isValid = rules[updates[i]]?.none { precedingUpdates.contains(it) } ?: true
                if (!isValid) {
                    break
                }
            }
            !isValid
        }
        .map {
            it.sortedWith(Comparator { o1, o2 ->
                val result: Int
                val ints = rules[o1] ?: listOf()
                result = if (ints.contains(o2)) {
                    -1
                } else {
                    1
                }
                result
            })
        }
        .map { updates -> updates[updates.size / 2] }
        .sum()
}

private val input: String by lazy {readInput()}

private fun readInput(): String {
    return object {}.javaClass.getResource("Day5.input").readText()
}
