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
    var nextPosition: Char

    do {
        matrix[y][x] = 'X'
        val nextY = y + direction.yMod
        val nextX = x + direction.xMod
        nextPosition = getNextPosition(matrix, nextY, nextX)

        when (nextPosition) {
            'E' -> break
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
    val input1 = input
    val matrix = getMatrix(input1)
    val height = matrix.size
    val guardIndex = input1.replace("\\n".toRegex(), "").indexOf("^")

    val y = guardIndex / height
    val x = guardIndex % height
    val direction = Direction.NORTH
    var counter = 0
    val path = fetchPath(matrix, y, x, direction).distinct()

    for (obstaclePoint in path) {
        matrix[obstaclePoint.first][obstaclePoint.second] = 'O'
        if (traverseMatrixPart2(matrix, y, x, direction)) counter++
        matrix[obstaclePoint.first][obstaclePoint.second] = '.'
    }

    return counter
}

private fun fetchPath(
    matrix: Array<CharArray>,
    startingY: Int,
    startingX: Int,
    startingDirection: Direction,
): List<Pair<Int, Int>> {
    var y = startingY
    var x = startingX
    var direction = startingDirection
    val path = mutableListOf<Pair<Int, Int>>()
    var nextPosition: Char

    do {
        val nextY = y + direction.yMod
        val nextX = x + direction.xMod
        nextPosition = getNextPosition(matrix, nextY, nextX)

        when (nextPosition) {
            'E' -> break
            '#' -> direction = direction.turn.value
            '.' -> { y = nextY; x = nextX; path.add(y to x) }
            '^' -> { y = nextY; x = nextX }
        }
    } while (true)

    return path
}


private fun traverseMatrixPart2(
    matrix: Array<CharArray>,
    startingY: Int,
    staringX: Int,
    startingDirection: Direction
): Boolean {
    val guard = GuardPosition(startingY, staringX, startingDirection)
    var metObstacles: MutableSet<GuardPosition>? = null
    var nextPosition: Char

    do {
        val nextY = guard.y + guard.direction.yMod
        val nextX = guard.x + guard.direction.xMod
        nextPosition = getNextPosition(matrix, nextY, nextX)

        when (nextPosition) {
            'E' -> return false
            '#', 'O' -> {
                if (nextPosition == 'O' && metObstacles == null) metObstacles = mutableSetOf()
                if (metObstacles?.contains(guard) == true) return true
                metObstacles?.add(guard.copy())
                guard.direction = guard.direction.turn.value
            }
            '.', '^' -> {
                guard.y = nextY
                guard.x = nextX
            }
        }
    } while (true)
}

private fun getMatrix(input: String): Array<CharArray> {
    return input.split("\n")
        .filter { it.isNotBlank() }
        .map { it.toCharArray() }
        .toTypedArray()
}

private val input: String by lazy { readInput() }

private fun readInput(): String {
    return object {}.javaClass.getResource("Day6.input").readText()
}

data class GuardPosition(var y: Int, var x: Int, var direction: Direction)

enum class Direction(val yMod: Int, val xMod: Int, val turn: Lazy<Direction>) {
    NORTH(-1, 0, lazy { EAST }),
    EAST(0, 1, lazy { SOUTH }),
    SOUTH(1, 0, lazy { WEST }),
    WEST(0, -1, lazy { NORTH })
}
