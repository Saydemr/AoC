package src

import java.util.*
import kotlin.math.abs

fun manhattanDistance(a: Triple<Int, Int, Int>, b: Triple<Int, Int, Int>): Int {
    return abs(a.first - b.first) + abs(a.second - b.second) + abs(a.third - b.third)
}

val directions = listOf(
    Triple(1, 0, 0),
    Triple(-1, 0, 0),
    Triple(0, 1, 0),
    Triple(0, -1, 0),
    Triple(0, 0, 1),
    Triple(0, 0, -1)
)


fun main() {

    val cubes = readInput("input18")
        .map { it.trim().split(",") }
        .map { Triple(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
        .toSet()


    var numNeighbors = 0
    cubes.forEach { current ->
        numNeighbors += 6 - cubes.filter { it2 -> it2 != current }.count { manhattanDistance(it, current) <= 1 }
    }

    println(numNeighbors)


    // part 2
    // find exterior surface
    val minX = cubes.minBy { it.first }.first - 1
    val maxX = cubes.maxBy { it.first }.first + 1
    val minY = cubes.minBy { it.second }.second - 1
    val maxY = cubes.maxBy { it.second }.second + 1
    val minZ = cubes.minBy { it.third }.third - 1
    val maxZ = cubes.maxBy { it.third }.third + 1

    fun isInRange(a: Triple<Int, Int, Int>): Boolean {
        return a.first in minX..maxX && a.second in minY..maxY && a.third in minZ..maxZ
    }

    val stack = Stack<Triple<Int, Int, Int>>()
    stack.push(Triple(minX, minY, minZ))

    val seen = mutableSetOf<Triple<Int, Int, Int>>()

    while (stack.isNotEmpty()) {
        val current = stack.pop()

        if (!seen.contains(current) && !cubes.contains(current) && isInRange(current)) {
            seen.add(current)
            directions.forEach { dir ->
                stack.push(Triple(current.first + dir.first, current.second + dir.second, current.third + dir.third))
            }
        }
    }

    var part2Neighbors = 0
    seen.forEach { current ->
        part2Neighbors += cubes.count { manhattanDistance(it, current) <= 1 }
    }

    println(part2Neighbors)

}