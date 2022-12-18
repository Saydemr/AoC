import kotlin.math.abs

fun manhattanDistance(a: Triple<Int, Int, Int>, b: Triple<Int, Int, Int>): Int {
    return abs(a.first - b.first) + abs(a.second - b.second) + abs(a.third - b.third)
}

fun main() {

    val list = readInput("../inputs/input18")
        .map { it.trim().split(",") }
        .map { Triple(it[0].toInt(), it[1].toInt(), it[2].toInt()) }



    val numNeighbors = mutableListOf<Int>()
    list.forEach { current ->
        val others = list.filter { it2 -> it2 != current }
            .count {  manhattanDistance(it, current) <= 1 }
        numNeighbors.add(6-others)

    }

    // find trapped locations (no cube but 6 neighbors)
    val minX = list.minBy { it.first }.first
    val maxX = list.maxBy { it.first }.first
    val minY = list.minBy { it.second }.second
    val maxY = list.maxBy { it.second }.second
    val minZ = list.minBy { it.third }.third
    val maxZ = list.maxBy { it.third }.third

    val trapped = mutableListOf<Triple<Int, Int, Int>>()
    for (x in minX..maxX) {
        for (y in minY..maxY) {
            for (z in minZ..maxZ) {
                val current = Triple(x, y, z)
                if (list.none { it == current }) {
                    val others = list.count {  manhattanDistance(it, current) <= 1 }
                    if (6-others == 0) {
                        trapped.add(current)
                    }
                }
            }
        }
    }

    println(numNeighbors.sum())
    println(numNeighbors.sum() - trapped.size*6)

    val numAdjacent = mutableListOf<Int>()


}