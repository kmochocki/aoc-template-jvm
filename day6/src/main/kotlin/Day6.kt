fun main() {
    println(part1())
    println(part2())
}

fun part1(): Any {
    val input1 = input
    val matrix = getMatrix(input1)
    val height = matrix.size
    val guardIndex = input1.replace("\\n".toRegex(), "").indexOf("^")

    var y = guardIndex / height
    var x = guardIndex % height
    var direction = Direction.NORTH
    var counter = 1

    do {
        matrix[y][x] = 'X'
        val nextY = y + direction.yMod
        val nextX = x + direction.xMod
        val nextPosition = getNextPosition(matrix, nextY, nextX)
        if (nextPosition == 'E') break

        when (nextPosition) {
            '#' -> direction = direction.turn.value
            '.' -> { y = nextY; x = nextX; counter++ }
            'X' -> { y = nextY; x = nextX }
        }
    } while (true)

    return counter
}

private fun getNextPosition(
    matrix: Array<CharArray>,
    nextY: Int,
    nextX: Int
): Char {
    return try {
        matrix[nextY][nextX]
    } catch (e: Exception) {
        'E'
    }
}

fun part2(): Any {
    return input.reversed()
}

private fun printMatrix(matrix: Array<CharArray>) {
    for (row in matrix) {
        for (element in row) {
            print(element)
        }
        println()
    }
    println()
}

private fun getMatrix(input: String): Array<CharArray> {
    return input.split("\n")
        .filter { it.isNotBlank() }
        .map { it.toCharArray() }
        .toTypedArray()
}

private val input: String by lazy {readInput()}

private fun readInput(): String {
    return object {}.javaClass.getResource("Day6.input").readText()
}

enum class Direction(val xMod: Int, val yMod: Int, val turn: Lazy<Direction>) {
    NORTH(0, -1, lazy { EAST }),
    EAST(1, 0, lazy { SOUTH }),
    SOUTH(0, 1, lazy { WEST }),
    WEST(-1, 0, lazy { NORTH })
}
