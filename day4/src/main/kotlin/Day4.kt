fun main() {
    println(part1())
    println(part2())
}

fun part1(): Any {
    val matrix = getMatrix()
    val height = matrix.size
    val width = matrix[0].size
    var xmasCount = 0
    for (i in 0 until height) {
        for (j in 0 until width) {
            if (matrix[i][j] == "X") {
                if (isDownMas(matrix, height, i, j)) xmasCount++
                if (isUpMas(matrix, i, j)) xmasCount++
                if (isRightMas(matrix, width, i, j)) xmasCount++
                if (isLeftMas(matrix, i, j)) xmasCount++
                if (isDownRightMas(matrix, height, width, i, j)) xmasCount++
                if (isDownLeftMas(matrix, height, i, j)) xmasCount++
                if (isUpRightMas(matrix, width, i, j)) xmasCount++
                if (isUpLeftMas(matrix, i, j)) xmasCount++
            }
        }
    }
    return xmasCount
}

private fun isDownMas(
    matrix: List<List<String>>,
    height: Int,
    i: Int,
    j: Int
) = i + 3 < height && isMas(
    matrix[i + 1][j],
    matrix[i + 2][j],
    matrix[i + 3][j]
)

private fun isUpMas(
    matrix: List<List<String>>,
    i: Int,
    j: Int
) = i - 3 >= 0 && isMas(
    matrix[i - 1][j],
    matrix[i - 2][j],
    matrix[i - 3][j]
)

private fun isRightMas(
    matrix: List<List<String>>,
    width: Int,
    i: Int,
    j: Int
) = j + 3 < width && isMas(
    matrix[i][j + 1],
    matrix[i][j + 2],
    matrix[i][j + 3]
)

private fun isLeftMas(
    matrix: List<List<String>>,
    i: Int,
    j: Int
) = j - 3 >= 0 && isMas(
    matrix[i][j - 1],
    matrix[i][j - 2],
    matrix[i][j - 3]
)

private fun isDownRightMas(
    matrix: List<List<String>>,
    height: Int,
    width: Int,
    i: Int,
    j: Int
) = i + 3 < height && j + 3 < width && isMas(
    matrix[i + 1][j + 1],
    matrix[i + 2][j + 2],
    matrix[i + 3][j + 3]
)

private fun isDownLeftMas(
    matrix: List<List<String>>,
    height: Int,
    i: Int,
    j: Int
) = i + 3 < height && j - 3 >= 0 && isMas(
    matrix[i + 1][j - 1],
    matrix[i + 2][j - 2],
    matrix[i + 3][j - 3]
)

private fun isUpRightMas(
    matrix: List<List<String>>,
    width: Int,
    i: Int,
    j: Int
) = i - 3 >= 0 && j + 3 < width && isMas(
    matrix[i - 1][j + 1],
    matrix[i - 2][j + 2],
    matrix[i - 3][j + 3]
)

private fun isUpLeftMas(
    matrix: List<List<String>>,
    i: Int,
    j: Int
) = i - 3 >= 0 && j - 3 >= 0 && isMas(
    matrix[i - 1][j - 1],
    matrix[i - 2][j - 2],
    matrix[i - 3][j - 3]
)

fun isMas(m: String, a: String, s: String): Boolean {
    return "M" == m && "A" == a && "S" == s
}

fun part2(): Any {
    val matrix = getMatrix()
    val height = matrix.size
    val width = matrix[0].size
    var masCount = 0
    for (i in 1 until height - 1) {
        for (j in 1 until width - 1) {
            if (matrix[i][j] == "A") {
                if (
                    (isMs(matrix[i - 1][j - 1], matrix[i + 1][j + 1]) ||
                            isMs(matrix[i + 1][j + 1], matrix[i - 1][j - 1])) &&
                    (isMs(matrix[i - 1][j + 1], matrix[i + 1][j - 1]) ||
                            isMs(matrix[i + 1][j - 1], matrix[i - 1][j + 1]))
                ) masCount++
            }
        }
    }
    return masCount
}

fun isMs(m: String, s: String): Boolean {
    return "M" == m && "S" == s
}

private fun getMatrix() = input.split("\n")
    .filter { it.isNotBlank() }
    .map { it.chunked(1) }

private val input: String by lazy {readInput()}

private fun readInput(): String {
    return object {}.javaClass.getResource("Day4.input").readText()
}
